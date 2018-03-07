import traceback


class RangerService:
    def __init__(self, battle_code, game_controller, game_module, pathing_module, combat_module):
        self.bc = battle_code
        self.gc = game_controller

        self.game_module = game_module
        self.pathing_module = pathing_module
        self.combat_module = combat_module

    def __shoot(self, unit):
        if not self.gc.is_attack_ready(unit.id):
            return

        # enemy unit in range => shoot
        for enemy in self.game_module.enemy_units():
            if self.combat_module.attack(unit, enemy):  # TODO: move targeting to combat module
                return enemy

    # taking damage => go towards base if taking damage
    def __defend(self, unit):
        my_pos = unit.location.map_location()

        current_field_damage = self.combat_module.enemy_damage_at(my_pos)
        if current_field_damage > 0:
            self.pathing_module.goto(unit, self.game_module.my_start_pos())

    # not taking damage => go towards enemy base
    def __attack(self, unit):
        my_pos = unit.location.map_location()

        current_field_damage = self.combat_module.enemy_damage_at(my_pos)
        if current_field_damage == 0:
            self.pathing_module.goto(unit, self.game_module.enemy_start_pos())

        else:
            self.__defend(unit)

    # TODO: Finish this method
    def __attackSynchronized(self, group):
        leader, units = group

        target = self.__shoot(leader)

        if target != None:
            for unit in units:
                if unit.attack_heat() > 10:
                    self.combat_module.attack(unit, target)

    def move(self, unit):
        try:
            if not unit.location.is_on_map():
                return

            if self.gc.round() < 100:
                self.__defend(unit)
            else:
                self.__attack(unit)

            self.__shoot(unit)
        except Exception as e:
            print('Error Ranger Service:', e)
            traceback.print_exc()
