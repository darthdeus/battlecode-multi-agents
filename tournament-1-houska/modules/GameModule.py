class GameModule:
    def __init__(self, battle_code, game_controller):
        self.bc = battle_code
        self.gc = game_controller

        # map stuff
        self.map = self.gc.starting_map(self.gc.planet())

        # team stuff
        self.team_my = self.gc.team()
        self.team_enemy = self.bc.Team.Red if self.team_my == self.bc.Team.Blue else self.bc.Team.Blue

        if self.gc.planet() == self.bc.Planet.Earth:
            self.__init_my_and_enemy_pos_earth()

        # enemy detection
        self.__last_refresh = 0
        self.__get_refresh_units()

        print("[INFO]: Game Module init successful...")

    # Inverts location for enemy's perspective, assumes Earth and point symetry
    def __invert_earth_loc(self, loc):
        x = self.map.width - loc.x
        y = self.map.height - loc.y
        return self.bc.MapLocation(self.bc.Planet.Earth, x, y)

    @staticmethod
    def __loc_to_string(loc):
        return '(' + str(loc.x) + ',' + str(loc.y) + ')'

    # inits mine starting position and presumed enemy position
    def __init_my_and_enemy_pos_earth(self):
        # it's possible to have mutliple units at different places, but let's start with this one
        self.my_start = self.gc.my_units()[0].location.map_location()
        self.enemy_start = self.__invert_earth_loc(self.my_start)

        # TODO: Remove logs
        print('worker starts at ' + self.__loc_to_string(self.my_start))
        print('enemy worker presumably at ' + self.__loc_to_string(self.enemy_start))

    # internal-call method to get visible enemies. Should not be used more than once a round
    def __get_refresh_units(self):
        enemies = []
        my_units = []

        for unit in self.gc.units():
            if not unit.location.is_on_map():
                continue

            if unit.team == self.team_enemy:
                enemies.append(unit)
            else:
                my_units.append(unit)

        self.__last_enemies = enemies
        self.__last_my_units = my_units

    # === API ===
    def my_start_pos(self):
        # TODO: Solve Mars stuff
        return self.my_start

    def enemy_start_pos(self):
        # TODO: Solve Mars stuff
        return self.enemy_start

    def enemy_units(self):
        # Caching
        if self.__last_refresh != self.gc.round():
            self.__last_refresh = self.gc.round()
            self.__get_refresh_units()

        return self.__last_enemies

    def my_units(self):
        if self.__last_refresh != self.gc.round():
            self.__last_refresh = self.gc.round()
            self.__get_refresh_units()

        return self.__last_my_units

    ## Returns dictionary of leaderUnit: list of units in group
    def getGroups(self, effectiveDistance, selector=lambda u: true):
        groups = {}
        for leader in filter(selector, gc.my_units()):
            if not unit.selected:
                unit.selected = true;
                groups[leader] = gc.sense_nearby_units_by_team(unit.location, effectiveDistance, gc.team)

        return groups

    def countGroupless(self):
        counter = 0
        for unit in gc.my_units():
            if isInGroup(unit):
                counter += 1

        return counter

    def isInGroup(self, unit):
        for l, g in gc.groups:
            if unit in g:
                return True

        return False
