from collections import deque
from utils.IntMap import *


class PathingModule:
    def __init__(self, battle_code, game_controller):
        self.bc = battle_code
        self.gc = game_controller

        self.planet = self.gc.planet()
        self.terrain_map = self.gc.starting_map(self.planet)

        self.tryRotate = [0, -1, 1, -2, 2]
        self.directions = list(self.bc.Direction)
        self.moveDirections = [
            self.bc.Direction.North, self.bc.Direction.Northeast,
            self.bc.Direction.East, self.bc.Direction.Southeast,
            self.bc.Direction.South, self.bc.Direction.Southwest,
            self.bc.Direction.West, self.bc.Direction.Northwest
        ]

        self.create_passability_map()

        print("[INFO]: Pathing Module init successful...")

    # inits passability map (0: can't get to, >0 same number <> inteconnected)
    def create_passability_map(self):
        neighbours = [
            (1, 0), (1, 1), (0, 1), (-1, 1),
            (-1, 0), (-1, -1), (0, -1), (1, -1)]
        pmap = IntMap(self.bc, self.gc)

        bfs_roots = []
        for unit in self.gc.my_units():
            if unit.location.is_on_planet(self.planet):
                unit_loc = unit.location.map_location()
                bfs_roots.append((unit_loc.x, unit_loc.y))

        root_index = 0
        for root in bfs_roots:
            # if root already visited, no need to BFS from it
            if pmap.get(Point(root[0], root[1])) > 0:
                continue

            # update interconnected area index
            root_index += 1
            self.__update_passability_map_from_root(root, root_index, pmap)

        # TODO: Remove debug output?
        self.passability_map = pmap
        print("created passability map:")
        pmap.printout()

    def __update_passability_map_from_root(self, root, root_index, pmap):
        neighbours = [
            (1, 0), (1, 1), (0, 1), (-1, 1),
            (-1, 0), (-1, -1), (0, -1), (1, -1)]

        # BFS from root
        bfs_queue = deque()
        bfs_queue.append(root)

        while bfs_queue:

            # update information about current location
            (x, y) = bfs_queue.popleft()
            loc = self.bc.MapLocation(self.planet, x, y)

            # set currently open location to current root_index
            pmap.set(loc, root_index)

            # go through all neighbours and add them to queue if legal and passable
            for (dx, dy) in neighbours:
                n = self.bc.MapLocation(self.planet, x + dx, y + dy)
                if (pmap.on_map(n) and pmap.get(n) == 0
                        and self.terrain_map.is_passable_terrain_at(n)):
                    # set location as open to not to add it multiple times
                    bfs_queue.append((x + dx, y + dy))
                    pmap.set(n, -1)

    # detect, if path exists between points according to the passability map
    def __can_pass(self, from_pos, to_pos):
        from_status = self.passability_map.get(from_pos)
        to_status = self.passability_map.get(to_pos)
        return True if (from_status == to_status) else False

    # fuzzy goto stuff copy-pasted from the lecture
    def __rotate(self, dir, amount):
        ind = self.moveDirections.index(dir)
        return self.moveDirections[(ind + amount) % 8]

    def __fuzzygoto(self, unit, dest):
        toward = unit.location.map_location().direction_to(dest)

        if toward != self.bc.Direction.Center:
            for tilt in self.tryRotate:
                d = self.__rotate(toward, tilt)

                if self.move_in_direction(unit, d):
                    return d

    # main goto method, used for unit movement
    def goto(self, unit, dest):
        if not self.gc.is_move_ready(unit.id):
            return

        return self.__fuzzygoto(unit, dest)

    # try to move in a direction. Return success
    def move_in_direction(self, unit, direction):
        if not self.gc.is_move_ready(unit.id):
            return False

        ret = self.gc.can_move(unit.id, direction)
        if ret:
            self.gc.move_robot(unit.id, direction)
        return ret

    # moves whole group closer to the target

    # check whether there are not many units around you
    # if there are not, move the samo way leader did, or get closer to him
    # if there are too many units around you - sit still
    def moveGroup(self, gc, group, target, closenesThreshold):
        leader, units = group
        leadereMovement = self.goto(leader, target)

        for unit in units:
            if len(gc.sense_nearby_units_by_team(unit.location, closnesThreshold, gc.team)) < len(units):
                if not moveInDirection(unit, leadereMovement):
                    self.goto(leader, target)
            # else:
            # do nothing - wait for rest to move asside
