from collections import namedtuple

Point = namedtuple('Point', ['x', 'y'])


class IntMap:
    def __init__(self, battle_code, game_controller):
        self.bc = battle_code
        self.gc = game_controller

        self.planet = self.gc.starting_map(self.gc.planet())

        self.width = self.planet.width
        self.height = self.planet.height

        self.arr = [[0] * self.height for i in range(self.width)]

    def on_map(self, loc):
        return (loc.x >= 0) and (loc.y >= 0) and (loc.x < self.width) and (loc.y < self.height)

    def get(self, mapLocation):
        if not self.on_map(mapLocation): return -1
        return self.arr[mapLocation.x][mapLocation.y]

    def set(self, mapLocation, val):
        self.arr[mapLocation.x][mapLocation.y] = val

    def printout(self):
        for y in range(self.height):
            buildstr = ''
            for x in range(self.width):
                buildstr += format(self.arr[x][self.height - 1 - y], '2d')
            print(buildstr)

    # Adds a value to each location within a certain disk around a location
    def add_disk(self, mapLocation, r2, val):
        locs = self.gc.all_locations_within(mapLocation, r2)
        for loc in locs:
            if self.on_map(loc):
                self.set(loc, self.get(loc) + val)

    # Element-wise muptilplication of two maps (good for map masks)
    def multiply(self, mmap2):
        for x in range(self.width):
            for y in range(self.height):
                ml = self.bc.MapLocation(self.planet, x, y)
                self.set(ml, self.get(ml) * mmap2.get(ml))

    # Returns location with highest number within a radius
    def find_highest(self, mapLocation, r2):
        locs = self.gc.all_locations_within(mapLocation, r2)
        best_val = -1
        best_loc = None
        for loc in locs:
            curr_val = self.get(loc)
            if curr_val > best_val:
                best_val = curr_val
                best_loc = loc
        return (best_val, best_loc)

    # Returns location with lowest number within a radius
    def find_lowest(self, mapLocation, r2):
        locs = self.gc.all_locations_within(mapLocation, r2)
        best_val = -1
        best_loc = None
        for loc in locs:
            curr_val = self.get(loc)
            if curr_val < best_val:
                best_val = curr_val
                best_loc = loc
        return (best_val, best_loc)
