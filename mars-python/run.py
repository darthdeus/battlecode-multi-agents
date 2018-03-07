import battlecode as bc
import random
import sys
import traceback
import time

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
gc.queue_research(bc.UnitType.Worker)
gc.queue_research(bc.UnitType.Rocket)
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

last_rocket_round = 0

while True:
    # We only support Python 3, which means brackets around print()
    print('pyround:', gc.round(), 'time left:', gc.get_time_left_ms(), 'ms')

    # frequent try/catches are a good idea
    try:
        # walk through our units:
        for unit in gc.my_units():
            # first, factory logic
            if unit.unit_type == bc.UnitType.Factory:

                garrison = unit.structure_garrison()
                if len(garrison) > 0:
                    d = random.choice(directions)
                    if gc.can_unload(unit.id, d):
                        print('Unloaded a robot!')
                        gc.unload(unit.id, d)
                        continue
                elif gc.can_produce_robot(unit.id, bc.UnitType.Worker):
                    gc.produce_robot(unit.id, bc.UnitType.Worker)
                    print('Produced a worker!')
                    continue
                elif gc.can_produce_robot(unit.id, bc.UnitType.Knight):
                    gc.produce_robot(unit.id, bc.UnitType.Knight)
                    print('Produced a knight!')
                    continue

            if unit.unit_type == bc.UnitType.Rocket:
                if unit.location.is_on_planet(bc.Planet.Earth):
                    if len(unit.structure_garrison()) > (750 - gc.round()) / 150:
                        marsLocation = bc.MapLocation(bc.Planet.Mars, random.randint(0, 20), random.randint(0, 20))
                        if gc.can_launch_rocket(unit.id, marsLocation):
                            gc.launch_rocket(unit.id, marsLocation)
                            print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM rocket launched!')
                            continue
                    else:
                        for robot in gc.my_units():
                            if gc.can_load(unit.id, robot.id):
                                gc.load(unit.id, robot.id)
                                print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM robot loaded to a rocket!')
                                continue
                else:
                    hasUnloaded = False
                    for direction in directions:
                        if gc.can_unload(unit.id, direction):
                            gc.unload(unit.id, direction)
                            print('MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM unloaded a rocket!')
                            hasUnloaded = True
                            break
                    if hasUnloaded:
                        continue

            d = random.choice(directions)

            if gc.round() > last_rocket_round + 100 and gc.karbonite() > bc.UnitType.Rocket.blueprint_cost() and gc.can_blueprint(
                    unit.id, bc.UnitType.Rocket, d):
                gc.blueprint(unit.id, bc.UnitType.Rocket, d)
                last_rocket_round = gc.round()
                print('Blueprinted a rocket!')
                continue

            # first, let's look for nearby blueprints to work on
            location = unit.location
            if location.is_on_map():
                nearby = gc.sense_nearby_units(location.map_location(), 2)
                for other in nearby:
                    if unit.unit_type == bc.UnitType.Worker and gc.can_build(unit.id, other.id):
                        gc.build(unit.id, other.id)
                        print('Built a factory!')
                        # move onto the next unit
                        continue

                    foo = False
                    for direction in directions:
                        if unit.unit_type == bc.UnitType.Worker and gc.can_harvest(unit.id, direction):
                            gc.harvest(unit.id, direction)
                            print('Harvested karbonite!')
                            foo = True
                            break
                    if foo:
                        continue
                    if other.team != my_team and gc.is_attack_ready(unit.id) and gc.can_attack(unit.id, other.id):
                        print('Attacked a thing!')
                        gc.attack(unit.id, other.id)
                        continue

            # or, try to build a factory:
            if gc.karbonite() > bc.UnitType.Factory.blueprint_cost() and gc.can_blueprint(unit.id, bc.UnitType.Factory,
                                                                                          d):
                gc.blueprint(unit.id, bc.UnitType.Factory, d)
            # and if that fails, try to move
            elif gc.is_move_ready(unit.id) and gc.can_move(unit.id, d):
                gc.move_robot(unit.id, d)

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
