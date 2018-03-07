import battlecode as bc
import random
import sys
import traceback

from modules.GameModule import GameModule
from modules.PathingModule import PathingModule
from modules.ResearchModule import ResearchModule
from modules.CombatModule import CombatModule
from modules.CarboniteModule import CarboniteModule

from units.HealerService import HealerService
from units.RangerService import RangerService
from units.WorkerService import WorkerService
from units.FactoryService import FactoryService

# init system
print("[INFO]: Player Start...")
random.seed(6137)

# game control
gc = bc.GameController()

# game state modules
game_module = GameModule(bc, gc)
pathing_module = PathingModule(bc, gc)
research_module = ResearchModule(bc, gc)
carbonite_module = CarboniteModule(bc, gc, pathing_module)
combat_module = CombatModule(bc, gc, game_module)

# unit control services
worker_service = WorkerService(bc, gc)
ranger_service = RangerService(bc, gc, game_module, pathing_module, combat_module)
healer_service = HealerService(gc, gc, game_module, pathing_module, combat_module)
factory_service = FactoryService(bc, gc)

# Queue initial research
research_module.base_strat()

print("[INFO]: Init Successful, Game Starting...")

while True:
    if (gc.round() % 10 == 0):
        print('pyround:', gc.round(), 'time left:', gc.get_time_left_ms(), 'ms', ' karbonite', gc.karbonite())

    try:

        #freelancers = filter(game_module.isInGroup, gc.my_units())

        for unit in gc.my_units():

            if unit.unit_type == bc.UnitType.Worker:
                worker_service.work(unit, factory_service.get_factories())

            if unit.unit_type == bc.UnitType.Factory:
                factory_service.produce(unit)
                factory_service.unload(unit)

            if unit.unit_type == bc.UnitType.Rocket:
                # TODO: whole rocket logic
                pass

            if unit.unit_type == bc.UnitType.Ranger:
                ranger_service.move(unit)

            if unit.unit_type == bc.UnitType.Healer:
                healer_service.move(unit)




    except Exception as e:
        print('ErrorALL:', e)
        # use this to show where the error was
        traceback.print_exc()

    # flush all logs & end turn
    sys.stdout.flush()
    sys.stderr.flush()
    gc.next_turn()
