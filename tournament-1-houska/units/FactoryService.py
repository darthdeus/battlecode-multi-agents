import random


class FactoryService():
    def __init__(self, bc, gc):
        self.gc = gc
        self.bc = bc

        self.directions = list(bc.Direction)

        self.last_factories = 0
        self.current_factories = 0
        self.round = 0
        self.unit_distribution = [self.bc.UnitType.Ranger, self.bc.UnitType.Ranger, self.bc.UnitType.Healer]

    def __update_counter(self):
        if self.gc.round() != self.round:
            self.last_factories = self.current_factories
            self.current_factories = 0
            self.round = self.gc.round()

    def get_factories(self):
        return self.last_factories

    def produce(self, unit):
        self.__update_counter()
        self.current_factories += 1
        type = random.choice(self.unit_distribution)
        if self.gc.can_produce_robot(unit.id, type):
            self.gc.produce_robot(unit.id, type)

    def unload(self, unit):
        garrison = unit.structure_garrison()
        if len(garrison) > 0:
            d = random.choice(self.directions)
            if self.gc.can_unload(unit.id, d):
                self.gc.unload(unit.id, d)