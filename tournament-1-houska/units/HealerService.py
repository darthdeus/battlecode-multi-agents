class HealerService:
    def __init__(self, battle_code, game_controller, game_module, pathing_module, combat_module):
        self.bc = battle_code
        self.gc = game_controller

        self.game_module = game_module
        self.pathing_module = pathing_module
        self.combat_module = combat_module

    def move(self, unit):
        # TODO healer logic
        pass
