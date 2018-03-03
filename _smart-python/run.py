import battlecode as bc
import random
import sys
import traceback
import time
# from itertools import filter

import os
print(os.getcwd())

print("pystarting")

# A GameController is the main type that you talk to the game with.
# Its constructor will connect to a running game.
gc = bc.GameController()
directions = list(bc.Direction)

print("pystarted")

# It's a good idea to try to keep your bots deterministic, to make debugging easier.
# determinism isn't required, but it means that the same things will happen in every thing you run,
# aside from turns taking slightly different amounts of time due to noise.
random.seed(1993)

# let's start off with some research!
# we can queue as much as we want.
gc.queue_research(bc.UnitType.Worker)
gc.queue_research(bc.UnitType.Rocket)
gc.queue_research(bc.UnitType.Worker)
gc.queue_research(bc.UnitType.Rocket)
gc.queue_research(bc.UnitType.Rocket)
gc.queue_research(bc.UnitType.Knight)
gc.queue_research(bc.UnitType.Ranger)
gc.queue_research(bc.UnitType.Mage)
gc.queue_research(bc.UnitType.Healer)
gc.queue_research(bc.UnitType.Knight)
gc.queue_research(bc.UnitType.Ranger)
gc.queue_research(bc.UnitType.Mage)
gc.queue_research(bc.UnitType.Healer)
gc.queue_research(bc.UnitType.Knight)
gc.queue_research(bc.UnitType.Ranger)
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
    bc.UnitType.Knight: wrap_on_map_only(combat_logic),
    bc.UnitType.Ranger: wrap_on_map_only(combat_logic),
    bc.UnitType.Mage:   wrap_on_map_only(combat_logic),
    bc.UnitType.Healer: wrap_on_map_only(combat_logic),
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
