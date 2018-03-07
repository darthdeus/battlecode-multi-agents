from utils.IntMap import IntMap


class CombatModule:
    def __init__(self, battle_code, game_controller, game_module):
        self.bc = battle_code
        self.gc = game_controller

        self.gm = game_module

        self.__last_refresh = 0
        self.__refresh_damage_map()

        print("[INFO]: Combat Module init successful...")

    def __refresh_damage_map(self):
        damage_map = IntMap(self.bc, self.gc)

        for enemy in self.gm.enemy_units():
            if enemy.unit_type == self.bc.UnitType.Factory: continue
            if enemy.unit_type == self.bc.UnitType.Rocket: continue

            damage_map.add_disk(enemy.location.map_location(), enemy.attack_range(), enemy.damage())

        self.__damage_map = damage_map

    # maximum damage enemy can deal at an position
    def enemy_damage_at(self, map_location):
        if self.__last_refresh != self.gc.round():
            self.__refresh_damage_map()
            self.__last_refresh = self.gc.round()

        return self.__damage_map.get(map_location)

    # Try to attack an enemy
    def attack(self, unit, enemy):
        if not self.gc.is_attack_ready(unit.id):
            return False

        if self.gc.can_attack(unit.id, enemy.id):
            self.gc.attack(unit.id, enemy.id)
            return True
        else:
            return False
