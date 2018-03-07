import battlecode as bc
import random
import sys
import traceback
import numpy as np
import time
# from itertools import filter

import os
print(os.getcwd())

print("pystarting")

# A GameController is the main type that you talk to the game with.
# Its constructor will connect to a running game.
gc = bc.GameController()

directions = [bc.Direction.North, bc.Direction.Northeast, bc.Direction.East, bc.Direction.Southeast, bc.Direction.South,
              bc.Direction.Southwest, bc.Direction.West, bc.Direction.Northwest]

#directions = list(bc.Direction) #tohle je pry divne orientovany a obsahuje to i center

print("pystarted")

# It's a good idea to try to keep your bots deterministic, to make debugging easier.
# determinism isn't required, but it means that the same things will happen in every thing you run,
# aside from turns taking slightly different amounts of time due to noise.
random.seed(1993)

# let's start off with some research!
# we can queue as much as we want.
gc.queue_research(bc.UnitType.Worker) # 25  // 25
gc.queue_research(bc.UnitType.Rocket) # 50  // 75
gc.queue_research(bc.UnitType.Ranger) # 25  // 100
gc.queue_research(bc.UnitType.Knight) # 25  // 125
gc.queue_research(bc.UnitType.Knight) # 25  // 200
gc.queue_research(bc.UnitType.Mage)   # 25  // 225
gc.queue_research(bc.UnitType.Mage)   # 75  // 300
gc.queue_research(bc.UnitType.Mage)   # 100 // 400
gc.queue_research(bc.UnitType.Knight) # 100 // 500 // Javelin
gc.queue_research(bc.UnitType.Rocket) # 100 // 600 // Faster travel time
gc.queue_research(bc.UnitType.Mage)   # 75  // 675 // Blink ale asi bude zabugovanej

# zbytek asi useless
gc.queue_research(bc.UnitType.Knight)
gc.queue_research(bc.UnitType.Ranger)
gc.queue_research(bc.UnitType.Healer)
gc.queue_research(bc.UnitType.Ranger)
gc.queue_research(bc.UnitType.Healer)
gc.queue_research(bc.UnitType.Knight)
gc.queue_research(bc.UnitType.Mage)
gc.queue_research(bc.UnitType.Healer)

my_team = gc.team()

rocket_count = 0

factory_priority = [bc.UnitType.Worker, bc.UnitType.Knight]


def first_dir_matching(pred):
    return next(filter(pred, directions), None)


def factory_logic(unit):
    if len(unit.structure_garrison()) > 0:
        unload_dir = first_dir_matching(lambda dir: gc.can_unload(unit.id, dir))
        if unload_dir is not None:
            gc.unload(unit.id, unload_dir)

    type = factory_priority[random.randint(0, 1)]

    global rocket_count
    rocket_level = gc.research_info().get_level(bc.UnitType.Rocket)
    if rocket_count == 0 and rocket_level > 0:
        return

    # for type in factory_priority:
    if gc.can_produce_robot(unit.id, type):
        gc.produce_robot(unit.id, type)
        return


def rocket_logic(unit):
    if unit.location.is_on_planet(bc.Planet.Earth):
        if len(unit.structure_garrison()) > (750 - gc.round()) / 150:
            marsLocation = bc.MapLocation(bc.Planet.Mars, random.randint(0, 20), random.randint(0, 20))
            if gc.can_launch_rocket(unit.id, marsLocation):
                gc.launch_rocket(unit.id, marsLocation)
                print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM rocket launched!')
        else:
            for robot in gc.my_units():
                if gc.can_load(unit.id, robot.id):
                    gc.load(unit.id, robot.id)
                    print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM robot loaded to a rocket!')
    else:
        for direction in directions:
            if gc.can_unload(unit.id, direction):
                gc.unload(unit.id, direction)
                print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM unloaded a rocket!')
                break


def wrap_on_map_only(f):
    def wrapper(unit):
        if unit.location.is_on_map():
            f(unit)

    return wrapper


def try_build(unit, type):
    if gc.karbonite() > type.blueprint_cost():
        valid_dir = first_dir_matching(lambda dir: gc.can_blueprint(unit.id, type, dir))
        if valid_dir is not None:
            if type == bc.UnitType.Rocket:
                print("!!!!!!!!!!!!Building a rocket!!!!!!!!!!!!")
                global rocket_count
                rocket_count += 1
            gc.blueprint(unit.id, type, valid_dir)
            return True
        elif type == bc.UnitType.Rocket:
            print("!!!! FAILED TO BUIL:D ROCKET, PLNO :(( !!!")

    return False


def worker_logic(unit):
    # Build if we can
    for other in gc.sense_nearby_units(unit.location.map_location(), 2):
        if gc.can_build(unit.id, other.id):
            gc.build(unit.id, other.id)
            return

    # Or place a blueprint
    build_successful = try_build(unit, bc.UnitType.Rocket) or try_build(unit, bc.UnitType.Factory)
    if build_successful:
        return

    # Or harvest
    available_dir = first_dir_matching(lambda dir: gc.can_harvest(unit.id, dir))
    if available_dir is not None and unit.unit_type == bc.UnitType.Worker:
        print('Harvested karbonite!')
        gc.harvest(unit.id, available_dir)
        return

    try_moving(unit)


def distance(unit_a, unit_b):
    return unit_a.location.map_location().distance_squared_to(unit_b.location.map_location())


def random_roam(unit):
    d = random.choice(directions)
    if gc.is_move_ready(unit.id) and gc.can_move(unit.id, d):
        gc.move_robot(unit.id, d)
    return


def mage_logic(unit):
    if unit.location.is_in_garrison() or unit.location.is_in_space():
        return

    attackable_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                   unit.attack_range()) if i.team != my_team]

    visible_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                   unit.vision_range()) if i.team != my_team]

    if len(attackable_enemies) > 0:
        if gc.is_attack_ready(unit):

            adepts = adepts = np.argsort([i.health for i in attackable_enemies if distance(unit, i) > 10])
            for adept in list(adepts):
                if gc.can_attack(unit.id, adept.id):
                    gc.attack(unit.id, adept.id)
                    break
        return

    elif len(visible_enemies) > 0:
        closest = np.argmin([distance(unit, i)
                               for i in visible_enemies])


        direction = unit.location.map_location.direction_to(visible_enemies[closest])
        if gc.is_move_ready(unit.id) and gc.can_move(unit.id, direction):
            gc.move_robot(unit.id, direction)
        return

    else:
        random_roam(unit)
        return


def ranger_logic(unit):

    if unit.location.is_in_garrison() or unit.location.is_in_space():
        return

    attackable_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                   unit.attack_range()) if i.team != my_team]

    visible_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                   unit.vision_range()) if i.team != my_team]

    if len(attackable_enemies) > 0:
        if gc.is_attack_ready(unit):

            # Try attack the closest, but not closer than 10
            adepts = np.argsort([distance(unit, i) for i in attackable_enemies if distance(unit, i) > 10])
            for adept in list(adepts):
                if gc.can_attack(unit.id, adept.id):
                    gc.attack(unit.id, adept.id)
                    break

            # Every enemy is too close, so I cannot attack anything!
            if len(adepts) == 0:
                possibilities = gc.all_locations_within(unit.location.map_location, radius_squared=1)
                evaluation = [i.distance_squared_to(adepts[-1]) for i in possibilities]
                best_direction = unit.location.map_location.direction_to(possibilities[int(np.argmax([evaluation]))])
                if gc.is_move_ready(unit.id) and gc.can_move(unit.id, best_direction):
                    gc.move_robot(unit.id, best_direction)
        return

    elif len(visible_enemies) > 0:
        closest = np.argmin([distance(unit, i)
                               for i in visible_enemies])
        ### Az bude search s distance heuristikou, tak zlepsit. Nyni asi jen vzdusnou carou ###
        direction = unit.location.map_location.direction_to(visible_enemies[closest])
        if gc.is_move_ready(unit.id) and gc.can_move(unit.id, direction):
            gc.move_robot(unit.id, direction)
        return

    else:
        random_roam(unit)
        return

def knight_logic(unit):

    if unit.location.is_in_garrison() or unit.location.is_in_space():
        return

    attackable_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                                           unit.attack_range()) if i.team != my_team]

    visible_enemies = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                                        unit.vision_range()) if i.team != my_team]

    if len(attackable_enemies) > 0:
        if gc.is_attack_ready(unit):

            # Attack one with lowest health pool to lower incoming dmg
            adepts = np.argsort([i.health for i in attackable_enemies if distance(unit, i) > 10])
            for adept in list(adepts):
                if gc.can_attack(unit.id, adept.id):
                    gc.attack(unit.id, adept.id)
                    break
        return

    elif len(visible_enemies) > 0:
        closest = np.argmin([distance(unit, i) for i in visible_enemies])

        direction = unit.location.map_location.direction_to(visible_enemies[closest])

        if gc.can_javelin(unit.id, visible_enemies[closest].id) and gc.is_javelin_ready(unit.id):
            gc.javelin(unit.id, visible_enemies[closest].id)

        if gc.is_move_ready(unit.id) and gc.can_move(unit.id, direction):
            gc.move_robot(unit.id, direction)
        return
    return




def healer_logic(unit):

    if unit.location.is_in_garrison() or unit.location.is_in_space():
        return

    teamates = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                                           unit.attack_range()) if i.team == my_team]

    for mate in teamates:
        if mate.health < mate.max_health - 10 and gc.is_heal_ready(unit.id):
            gc.heal(unit.id, mate.id)
            return

    out_of_range_teamates = [i for i in gc.sense_nearby_units(unit.location.map_location(),
                                                 unit.attack_range()) if i.team == my_team]
    if gc.is_move_ready(unit.id):
        closest = np.argmin([distance(unit, i)
                             for i in out_of_range_teamates])

        direction = unit.location.map_location.direction_to(out_of_range_teamates[closest])

        ## zatim vzdusnou carou
        if gc.is_move_ready(unit.id) and gc.can_move(unit.id, direction):
            gc.move_robot(unit.id, direction)

        if len(out_of_range_teamates) == 0:
            random_roam(unit)
    return


def combat_logic(unit):
    for other in gc.sense_nearby_units(unit.location.map_location(), 2):
        if other.team != my_team and gc.is_attack_ready(unit.id) and gc.can_attack(unit.id, other.id):
            gc.attack(unit.id, other.id)
            return

    try_moving(unit)

def try_moving(unit):
    if gc.is_move_ready(unit.id):
        unit_location = unit.location.map_location()

        friends = [other for other in gc.sense_nearby_units(unit_location, 1) if other.team == my_team]

        for friend in friends:
            loc = friend.location.map_location()
            opposite = unit_location.direction_to(loc).opposite()

            if gc.can_move(unit.id, opposite):
                gc.move_robot(unit.id, opposite)
                return

        possible_move = first_dir_matching(lambda dir: gc.can_move(unit.id, dir))
        if possible_move is not None:
            gc.move_robot(unit.id, possible_move)


unit_type_callbacks = {
    bc.UnitType.Factory: factory_logic,
    bc.UnitType.Rocket: wrap_on_map_only(rocket_logic),
    bc.UnitType.Worker: wrap_on_map_only(worker_logic),
    bc.UnitType.Knight: wrap_on_map_only(knight_logic),
    bc.UnitType.Ranger: wrap_on_map_only(ranger_logic),
    bc.UnitType.Mage:   wrap_on_map_only(mage_logic),
    bc.UnitType.Healer: wrap_on_map_only(healer_logic),
}

while True:
    # We only support Python 3, which means brackets around print()
    # print('pyround:', gc.round(), 'time left:', gc.get_time_left_ms(), 'ms')

    # frequent try/catches are a good idea
    try:
        # walk through our units:
        for unit in gc.my_units():
            if unit.unit_type in unit_type_callbacks.keys():
                unit_type_callbacks[unit.unit_type](unit)
        
            else:
                print("Unhandled unit type: {}".format(unit.unit_type))
            #
            # d = random.choice(directions)
            #
            # # first, let's look for nearby blueprints to work on
            # location = unit.location
            # if location.is_on_map():
            #     nearby = gc.sense_nearby_units(location.map_location(), 2)
            #     for other in nearby:
            #         if unit.unit_type == bc.UnitType.Worker and gc.can_build(unit.id, other.id):
            #             gc.build(unit.id, other.id)
            #             print('Built a factory!')
            #             # move onto the next unit
            #             continue
            #
            #         available_dir = next(filter(lambda dir: gc.can_harvest(unit.id, dir), directions), None)
            #         if available_dir is not None and unit.unit_type == bc.UnitType.Worker:
            #             print('Harvested karbonite!')
            #             gc.harvest(unit.id, available_dir)
            #             continue
            #
            #         if other.team != my_team and gc.is_attack_ready(unit.id) and gc.can_attack(unit.id, other.id):
            #             print('Attacked a thing!')
            #             gc.attack(unit.id, other.id)
            #             continue
            #
            # # and if that fails, try to move
            # elif gc.is_move_ready(unit.id) and gc.can_move(unit.id, d):
            #     gc.move_robot(unit.id, d)

    except Exception as e:
        print('Error:', e)
        # use this to show where the error was
        traceback.print_exc()

    # send the actions we've performed, and wait for our next turn.
    gc.next_turn()

    # these lines are not strictly necessary, but it helps make the logs make more sense.
    # it forces everything we've written this turn to be written to the manager.
    sys.stdout.flush()
    sys.stderr.flush()


#BFS
def get_neighbours(pos, map):
    # pos =(Int,Int)
    # map = GameMap
    isinstance(pos, )
    list = set()
    for i in range(-1, 2):
        for j in range(-1, 2):
            if i + pos[0] >= map.width or i + pos[0] < 0 or j + pos[1] >= map.height or j + pos[1] < 0 or (
                            j == 0 and i == 0):
                continue
            list.add((pos[0] + i, pos[1] + j))
    return list


def bfs(start, map, filter, condition):
    # start = (Int,Int)
    # map = GameMap
    # filter = ((Int,Int),GameMap) -> Boolean # not suitable position
    # condition = ((Int,Int),GameMap) -> Boolean # wanted possition
    visited, queue = set(), [start]
    while queue:
        v = queue.pop(0)
        if condition(v, map):
            return bc.MapLocation(map.planet, v[0], v[1])
        if v not in visited and filter(v, map):
            visited.add(v)
            queue.extend(get_neighbours(v, map) - visited)
    return None


def is_accessible(loc, map):
    # loc = (Int,Int)
    # map = GameMap
    l = bc.MapLocation(map.planet, loc[0], loc[1])
    return map.is_passable_terrain_at(l)


def bfs_path(start,map,filter,condition):
    queue = [(start,[start])]
    while queue:
        (vertex,path) = queue.pop(0)
        following = get_neighbours(vertex,map) - set(path)
        for i in following:
            if condition(i):
                yield path + [i]
            else:
                if filter(i,map):
                    queue.append((i, path+[i]))
