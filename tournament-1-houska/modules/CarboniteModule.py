from collections import deque
from utils.IntMap import *

class CarboniteModule:
    def __init__(self, battle_code, game_controller, pathing_module):
        self.bc = battle_code
        self.gc = game_controller

        self.terrain_map = pathing_module.terrain_map
        self.passability_map = pathing_module.passability_map

        self.create_carbonite_info()

        print("[INFO]: Carbonite Module init successful...")

    def create_carbonite_info(self):
        map = IntMap(self. bc, self.gc)
        map_index = IntMap(self. bc, self.gc)
        deposits = []




        self.map = map #Int map of amount of carbonite
        self.map_index = map_index #Int map of indexes into deposits array
        self.deposits = deposits #List of (list of neighbouring carbonite nodes, overall number of carbonite in deposit)

        return

    #updates the info
    def update_carbonite(self, location, amount):
        return