import battlecode as bc


class ResearchModule:
    def __init__(self, battle_code, game_controller):
        self.bc = battle_code
        self.gc = game_controller

    def base_strat(self):
        if self.gc.planet() == self.bc.Planet.Earth:
            print("queue initial research")
            self.gc.queue_research(self.bc.UnitType.Worker) #25
            self.gc.queue_research(self.bc.UnitType.Ranger) #25
            self.gc.queue_research(self.bc.UnitType.Healer) #25
            self.gc.queue_research(self.bc.UnitType.Ranger) #100
            self.gc.queue_research(self.bc.UnitType.Healer) #100
            self.gc.queue_research(self.bc.UnitType.Ranger) #200 ranger full after 475
            self.gc.queue_research(self.bc.UnitType.Worker) #75
            self.gc.queue_research(self.bc.UnitType.Rocket) #50 usable rocket after 555
            self.gc.queue_research(self.bc.UnitType.Healer) #100 healer full after 655
            self.gc.queue_research(self.bc.UnitType.Worker) #75
            self.gc.queue_research(self.bc.UnitType.Worker) #75

        print("[INFO]: Research Module init successful...")

    def research(self, unit):
        self.gc.queue_research(unit)
        print("researching")
