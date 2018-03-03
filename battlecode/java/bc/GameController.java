/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class GameController {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected GameController(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(GameController obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_GameController(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public GameController() {
    this(bcJNI.new_GameController(), true);
  }

  public void nextTurn() {
    bcJNI.GameController_nextTurn(swigCPtr, this);
  }

  public int getTimeLeftMs() {
    return bcJNI.GameController_getTimeLeftMs(swigCPtr, this);
  }

  public long round() {
    return bcJNI.GameController_round(swigCPtr, this);
  }

  public Planet planet() {
    return Planet.swigToEnum(bcJNI.GameController_planet(swigCPtr, this));
  }

  public Team team() {
    return Team.swigToEnum(bcJNI.GameController_team(swigCPtr, this));
  }

  public PlanetMap startingMap(Planet planet) {
    long cPtr = bcJNI.GameController_startingMap(swigCPtr, this, planet.swigValue());
    return (cPtr == 0) ? null : new PlanetMap(cPtr, true);
  }

  public long karbonite() {
    return bcJNI.GameController_karbonite(swigCPtr, this);
  }

  public Unit unit(int id) {
    long cPtr = bcJNI.GameController_unit(swigCPtr, this, id);
    return (cPtr == 0) ? null : new Unit(cPtr, true);
  }

  public VecUnit units() {
    long cPtr = bcJNI.GameController_units(swigCPtr, this);
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public VecUnit myUnits() {
    long cPtr = bcJNI.GameController_myUnits(swigCPtr, this);
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public VecUnit unitsInSpace() {
    long cPtr = bcJNI.GameController_unitsInSpace(swigCPtr, this);
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public long karboniteAt(MapLocation location) {
    return bcJNI.GameController_karboniteAt(swigCPtr, this, MapLocation.getCPtr(location), location);
  }

  public VecMapLocation allLocationsWithin(MapLocation location, long radius_squared) {
    long cPtr = bcJNI.GameController_allLocationsWithin(swigCPtr, this, MapLocation.getCPtr(location), location, radius_squared);
    return (cPtr == 0) ? null : new VecMapLocation(cPtr, true);
  }

  public boolean canSenseLocation(MapLocation location) {
    return bcJNI.GameController_canSenseLocation(swigCPtr, this, MapLocation.getCPtr(location), location);
  }

  public boolean canSenseUnit(int id) {
    return bcJNI.GameController_canSenseUnit(swigCPtr, this, id);
  }

  public VecUnit senseNearbyUnits(MapLocation location, long radius) {
    long cPtr = bcJNI.GameController_senseNearbyUnits(swigCPtr, this, MapLocation.getCPtr(location), location, radius);
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public VecUnit senseNearbyUnitsByTeam(MapLocation location, long radius, Team team) {
    long cPtr = bcJNI.GameController_senseNearbyUnitsByTeam(swigCPtr, this, MapLocation.getCPtr(location), location, radius, team.swigValue());
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public VecUnit senseNearbyUnitsByType(MapLocation location, long radius, UnitType unit_type) {
    long cPtr = bcJNI.GameController_senseNearbyUnitsByType(swigCPtr, this, MapLocation.getCPtr(location), location, radius, unit_type.swigValue());
    return (cPtr == 0) ? null : new VecUnit(cPtr, true);
  }

  public boolean hasUnitAtLocation(MapLocation location) {
    return bcJNI.GameController_hasUnitAtLocation(swigCPtr, this, MapLocation.getCPtr(location), location);
  }

  public Unit senseUnitAtLocation(MapLocation location) {
    long cPtr = bcJNI.GameController_senseUnitAtLocation(swigCPtr, this, MapLocation.getCPtr(location), location);
    return (cPtr == 0) ? null : new Unit(cPtr, true);
  }

  public AsteroidPattern asteroidPattern() {
    long cPtr = bcJNI.GameController_asteroidPattern(swigCPtr, this);
    return (cPtr == 0) ? null : new AsteroidPattern(cPtr, true);
  }

  public OrbitPattern orbitPattern() {
    long cPtr = bcJNI.GameController_orbitPattern(swigCPtr, this);
    return (cPtr == 0) ? null : new OrbitPattern(cPtr, true);
  }

  public long currentDurationOfFlight() {
    return bcJNI.GameController_currentDurationOfFlight(swigCPtr, this);
  }

  public Veci32 getTeamArray(Planet planet) {
    long cPtr = bcJNI.GameController_getTeamArray(swigCPtr, this, planet.swigValue());
    return (cPtr == 0) ? null : new Veci32(cPtr, true);
  }

  public void writeTeamArray(long index, int value) {
    bcJNI.GameController_writeTeamArray(swigCPtr, this, index, value);
  }

  public void disintegrateUnit(int unit_id) {
    bcJNI.GameController_disintegrateUnit(swigCPtr, this, unit_id);
  }

  public short isOccupiable(MapLocation location) {
    return bcJNI.GameController_isOccupiable(swigCPtr, this, MapLocation.getCPtr(location), location);
  }

  public boolean canMove(int robot_id, Direction direction) {
    return bcJNI.GameController_canMove(swigCPtr, this, robot_id, direction.swigValue());
  }

  public boolean isMoveReady(int robot_id) {
    return bcJNI.GameController_isMoveReady(swigCPtr, this, robot_id);
  }

  public void moveRobot(int robot_id, Direction direction) {
    bcJNI.GameController_moveRobot(swigCPtr, this, robot_id, direction.swigValue());
  }

  public boolean canAttack(int robot_id, int target_unit_id) {
    return bcJNI.GameController_canAttack(swigCPtr, this, robot_id, target_unit_id);
  }

  public boolean isAttackReady(int robot_id) {
    return bcJNI.GameController_isAttackReady(swigCPtr, this, robot_id);
  }

  public void attack(int robot_id, int target_unit_id) {
    bcJNI.GameController_attack(swigCPtr, this, robot_id, target_unit_id);
  }

  public ResearchInfo researchInfo() {
    long cPtr = bcJNI.GameController_researchInfo(swigCPtr, this);
    return (cPtr == 0) ? null : new ResearchInfo(cPtr, true);
  }

  public short resetResearch() {
    return bcJNI.GameController_resetResearch(swigCPtr, this);
  }

  public short queueResearch(UnitType branch) {
    return bcJNI.GameController_queueResearch(swigCPtr, this, branch.swigValue());
  }

  public boolean canHarvest(int worker_id, Direction direction) {
    return bcJNI.GameController_canHarvest(swigCPtr, this, worker_id, direction.swigValue());
  }

  public void harvest(int worker_id, Direction direction) {
    bcJNI.GameController_harvest(swigCPtr, this, worker_id, direction.swigValue());
  }

  public boolean canBlueprint(int worker_id, UnitType unit_type, Direction direction) {
    return bcJNI.GameController_canBlueprint(swigCPtr, this, worker_id, unit_type.swigValue(), direction.swigValue());
  }

  public void blueprint(int worker_id, UnitType structure_type, Direction direction) {
    bcJNI.GameController_blueprint(swigCPtr, this, worker_id, structure_type.swigValue(), direction.swigValue());
  }

  public boolean canBuild(int worker_id, int blueprint_id) {
    return bcJNI.GameController_canBuild(swigCPtr, this, worker_id, blueprint_id);
  }

  public void build(int worker_id, int blueprint_id) {
    bcJNI.GameController_build(swigCPtr, this, worker_id, blueprint_id);
  }

  public boolean canRepair(int worker_id, int structure_id) {
    return bcJNI.GameController_canRepair(swigCPtr, this, worker_id, structure_id);
  }

  public void repair(int worker_id, int structure_id) {
    bcJNI.GameController_repair(swigCPtr, this, worker_id, structure_id);
  }

  public boolean canReplicate(int worker_id, Direction direction) {
    return bcJNI.GameController_canReplicate(swigCPtr, this, worker_id, direction.swigValue());
  }

  public void replicate(int worker_id, Direction direction) {
    bcJNI.GameController_replicate(swigCPtr, this, worker_id, direction.swigValue());
  }

  public boolean canJavelin(int knight_id, int target_unit_id) {
    return bcJNI.GameController_canJavelin(swigCPtr, this, knight_id, target_unit_id);
  }

  public boolean isJavelinReady(int knight_id) {
    return bcJNI.GameController_isJavelinReady(swigCPtr, this, knight_id);
  }

  public void javelin(int knight_id, int target_unit_id) {
    bcJNI.GameController_javelin(swigCPtr, this, knight_id, target_unit_id);
  }

  public boolean canBeginSnipe(int ranger_id, MapLocation location) {
    return bcJNI.GameController_canBeginSnipe(swigCPtr, this, ranger_id, MapLocation.getCPtr(location), location);
  }

  public boolean isBeginSnipeReady(int ranger_id) {
    return bcJNI.GameController_isBeginSnipeReady(swigCPtr, this, ranger_id);
  }

  public void beginSnipe(int ranger_id, MapLocation location) {
    bcJNI.GameController_beginSnipe(swigCPtr, this, ranger_id, MapLocation.getCPtr(location), location);
  }

  public boolean canBlink(int mage_id, MapLocation location) {
    return bcJNI.GameController_canBlink(swigCPtr, this, mage_id, MapLocation.getCPtr(location), location);
  }

  public boolean isBlinkReady(int mage_id) {
    return bcJNI.GameController_isBlinkReady(swigCPtr, this, mage_id);
  }

  public void blink(int mage_id, MapLocation location) {
    bcJNI.GameController_blink(swigCPtr, this, mage_id, MapLocation.getCPtr(location), location);
  }

  public boolean canHeal(int healer_id, int target_robot_id) {
    return bcJNI.GameController_canHeal(swigCPtr, this, healer_id, target_robot_id);
  }

  public boolean isHealReady(int healer_id) {
    return bcJNI.GameController_isHealReady(swigCPtr, this, healer_id);
  }

  public void heal(int healer_id, int target_robot_id) {
    bcJNI.GameController_heal(swigCPtr, this, healer_id, target_robot_id);
  }

  public boolean canOvercharge(int healer_id, int target_robot_id) {
    return bcJNI.GameController_canOvercharge(swigCPtr, this, healer_id, target_robot_id);
  }

  public boolean isOverchargeReady(int healer_id) {
    return bcJNI.GameController_isOverchargeReady(swigCPtr, this, healer_id);
  }

  public void overcharge(int healer_id, int target_robot_id) {
    bcJNI.GameController_overcharge(swigCPtr, this, healer_id, target_robot_id);
  }

  public boolean canLoad(int structure_id, int robot_id) {
    return bcJNI.GameController_canLoad(swigCPtr, this, structure_id, robot_id);
  }

  public void load(int structure_id, int robot_id) {
    bcJNI.GameController_load(swigCPtr, this, structure_id, robot_id);
  }

  public boolean canUnload(int structure_id, Direction direction) {
    return bcJNI.GameController_canUnload(swigCPtr, this, structure_id, direction.swigValue());
  }

  public void unload(int structure_id, Direction direction) {
    bcJNI.GameController_unload(swigCPtr, this, structure_id, direction.swigValue());
  }

  public boolean canProduceRobot(int factory_id, UnitType robot_type) {
    return bcJNI.GameController_canProduceRobot(swigCPtr, this, factory_id, robot_type.swigValue());
  }

  public void produceRobot(int factory_id, UnitType robot_type) {
    bcJNI.GameController_produceRobot(swigCPtr, this, factory_id, robot_type.swigValue());
  }

  public RocketLandingInfo rocketLandings() {
    long cPtr = bcJNI.GameController_rocketLandings(swigCPtr, this);
    return (cPtr == 0) ? null : new RocketLandingInfo(cPtr, true);
  }

  public boolean canLaunchRocket(int rocket_id, MapLocation destination) {
    return bcJNI.GameController_canLaunchRocket(swigCPtr, this, rocket_id, MapLocation.getCPtr(destination), destination);
  }

  public void launchRocket(int rocket_id, MapLocation location) {
    bcJNI.GameController_launchRocket(swigCPtr, this, rocket_id, MapLocation.getCPtr(location), location);
  }

  public StartGameMessage startGame(Player player) {
    long cPtr = bcJNI.GameController_startGame(swigCPtr, this, Player.getCPtr(player), player);
    return (cPtr == 0) ? null : new StartGameMessage(cPtr, true);
  }

  public TurnApplication applyTurn(TurnMessage turn, int time_left_ms) {
    long cPtr = bcJNI.GameController_applyTurn(swigCPtr, this, TurnMessage.getCPtr(turn), turn, time_left_ms);
    return (cPtr == 0) ? null : new TurnApplication(cPtr, true);
  }

  public InitialTurnApplication initialStartTurnMessage(int time_left_ms) {
    long cPtr = bcJNI.GameController_initialStartTurnMessage(swigCPtr, this, time_left_ms);
    return (cPtr == 0) ? null : new InitialTurnApplication(cPtr, true);
  }

  public boolean isOver() {
    return bcJNI.GameController_isOver(swigCPtr, this);
  }

  public Team winningTeam() {
    return Team.swigToEnum(bcJNI.GameController_winningTeam(swigCPtr, this));
  }

  public String managerViewerMessage() {
    return bcJNI.GameController_managerViewerMessage(swigCPtr, this);
  }

  public void printGameAnsi() {
    bcJNI.GameController_printGameAnsi(swigCPtr, this);
  }

  public long managerKarbonite(Team team) {
    return bcJNI.GameController_managerKarbonite(swigCPtr, this, team.swigValue());
  }

}
