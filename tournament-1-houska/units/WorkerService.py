import random


class WorkerService():
    def __init__(self, bc, gc):
        self.gc = gc
        self.bc = bc

        self.directions = list(bc.Direction)
        self.MIN_WORKERS = 10
        self.MIN_FACTORIES = 7
        self.last_workers = 3
        self.current_workers = 0
        self.round = 0

    def __update_counter(self):
        if self.gc.round() != self.round:
            self.last_workers = self.current_workers
            self.current_workers = 0
            self.round = self.gc.round()

    # Worker strategy:
    # if you can build something near you, do it
    # if we need new blueprint, create it
    # duplicate if needed
    # mine karbonite if you can
    # otherwise mote to find more carbonite
    def work(self, unit, factories):
        self.__update_counter()
        self.current_workers += 1

        builder = 0
        # if you can build something near you, do it
        try:
            nearby = self.gc.sense_nearby_units(unit.location.map_location(), 2)
            for other in nearby:
                if (
                        other.unit_type == self.bc.UnitType.Factory or other.unit_type == self.bc.UnitType.Rocket) and self.gc.can_build(
                        unit.id, other.id):
                    self.gc.build(unit.id, other.id)
                    builder = 1
        except Exception as e:
            print('ErrorW1:', e)

        # if we need new blueprint, create it
        try:
            d = random.choice(self.directions)
            if factories < self.MIN_FACTORIES and self.gc.can_blueprint(unit.id, self.bc.UnitType.Factory, d):
                self.gc.blueprint(unit.id, self.bc.UnitType.Factory, d)
        except Exception as e:
            print('ErrorW2:', e)
        # duplicate if needed

        try:
            d = random.choice(self.directions)
            if self.last_workers < self.MIN_WORKERS and self.gc.can_replicate(unit.id, d):  # TODO
                self.gc.replicate(unit.id, d)
        except Exception as e:
            print('ErrorW3:', e)
        # mine karbonite if you can
        try:
            d = random.choice(self.directions)
            if self.gc.can_harvest(unit.id, self.bc.Direction.Center):
                self.gc.harvest(unit.id, self.bc.Direction.Center)
            elif builder == 0 and self.gc.can_move(unit.id, d) and self.gc.is_move_ready(unit.id):
                self.gc.move_robot(unit.id, d)
        except Exception as e:
            print('ErrorW4:', e)