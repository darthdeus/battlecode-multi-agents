/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

import java.lang.*; // For Exception
import java.io.*;
import java.net.*;

public class bcJNI {

    static {
        System.out.println("-- Starting battlecode java engine, vroom vroom! --");

        URL main = bcJNI.class.getResource("bcJNI.class");
        if (!"file".equalsIgnoreCase(main.getProtocol()))
            throw new IllegalStateException("Battlecode engine has to be left as loose class files - no jars please. Sorry.");
        File path = new File(main.getPath());
        File parent = path.getParentFile();

        System.out.println("-- Note: you may get a warning about stack guards, please ignore it. --");

        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.indexOf("win") >= 0) {
                String lib = new File(parent, "libbattlecode-java-win32.dll").getAbsolutePath();
                System.out.println("Loading windows library: "+lib);
                System.load(lib);
            } else if (os.indexOf("mac") >= 0) {
                String lib = new File(parent, "libbattlecode-java-darwin.so").getAbsolutePath();
                System.out.println("Loading mac library: "+lib);
                System.load(lib);
            } else if (os.indexOf("nux") >= 0) {
                String lib = new File(parent, "libbattlecode-java-linux.so").getAbsolutePath();
                System.out.println("Loading linux library: "+lib);
                System.load(lib);
            } else {
                throw new Exception("Unknown operating system (good job, please install linux now): " + os);
            }
        } catch (Exception e) {
            System.err.println("Native code library failed to load. Does the file just printed exist?");
            System.err.println("Error: "+e);
            System.exit(1);
        }
        System.out.println("-- Engine loaded. --");
    }

  public final static native int bcPlanetOther(int jarg1);
  public final static native String bcPlanetDebug(int jarg1);
  public final static native boolean bcPlanetEq(int jarg1, int jarg2);
  public final static native int bcPlanetFromJson(String jarg1);
  public final static native String bcPlanetToJson(int jarg1);
  public final static native int bcDirectionDx(int jarg1);
  public final static native int bcDirectionDy(int jarg1);
  public final static native boolean bcDirectionIsDiagonal(int jarg1);
  public final static native int bcDirectionOpposite(int jarg1);
  public final static native int bcDirectionRotateLeft(int jarg1);
  public final static native int bcDirectionRotateRight(int jarg1);
  public final static native int bcDirectionFromJson(String jarg1);
  public final static native String bcDirectionToJson(int jarg1);
  public final static native long new_MapLocation(int jarg1, int jarg2, int jarg3);
  public final static native void delete_MapLocation(long jarg1);
  public final static native long MapLocation_add(long jarg1, MapLocation jarg1_, int jarg2);
  public final static native long MapLocation_subtract(long jarg1, MapLocation jarg1_, int jarg2);
  public final static native long MapLocation_addMultiple(long jarg1, MapLocation jarg1_, int jarg2, int jarg3);
  public final static native long MapLocation_translate(long jarg1, MapLocation jarg1_, int jarg2, int jarg3);
  public final static native long MapLocation_distanceSquaredTo(long jarg1, MapLocation jarg1_, long jarg2, MapLocation jarg2_);
  public final static native int MapLocation_directionTo(long jarg1, MapLocation jarg1_, long jarg2, MapLocation jarg2_);
  public final static native boolean MapLocation_isAdjacentTo(long jarg1, MapLocation jarg1_, long jarg2, MapLocation jarg2_);
  public final static native boolean MapLocation_isWithinRange(long jarg1, MapLocation jarg1_, long jarg2, long jarg3, MapLocation jarg3_);
  public final static native String MapLocation_toString(long jarg1, MapLocation jarg1_);
  public final static native long MapLocation_clone(long jarg1, MapLocation jarg1_);
  public final static native boolean MapLocation_equals(long jarg1, MapLocation jarg1_, long jarg2, MapLocation jarg2_);
  public final static native String MapLocation_toJson(long jarg1, MapLocation jarg1_);
  public final static native void MapLocation_planet_set(long jarg1, MapLocation jarg1_, int jarg2);
  public final static native int MapLocation_planet_get(long jarg1, MapLocation jarg1_);
  public final static native void MapLocation_x_set(long jarg1, MapLocation jarg1_, int jarg2);
  public final static native int MapLocation_x_get(long jarg1, MapLocation jarg1_);
  public final static native void MapLocation_y_set(long jarg1, MapLocation jarg1_, int jarg2);
  public final static native int MapLocation_y_get(long jarg1, MapLocation jarg1_);
  public final static native long bcMapLocationFromJson(String jarg1);
  public final static native long new_VecMapLocation();
  public final static native void delete_VecMapLocation(long jarg1);
  public final static native String VecMapLocation_toString(long jarg1, VecMapLocation jarg1_);
  public final static native long VecMapLocation_clone(long jarg1, VecMapLocation jarg1_);
  public final static native long VecMapLocation_size(long jarg1, VecMapLocation jarg1_);
  public final static native long VecMapLocation_get(long jarg1, VecMapLocation jarg1_, long jarg2);
  public final static native long new_Veci32();
  public final static native void delete_Veci32(long jarg1);
  public final static native String Veci32_toString(long jarg1, Veci32 jarg1_);
  public final static native long Veci32_clone(long jarg1, Veci32 jarg1_);
  public final static native long Veci32_size(long jarg1, Veci32 jarg1_);
  public final static native int Veci32_get(long jarg1, Veci32 jarg1_, long jarg2);
  public final static native long new_Location();
  public final static native void delete_Location(long jarg1);
  public final static native boolean Location_isOnMap(long jarg1, Location jarg1_);
  public final static native boolean Location_isOnPlanet(long jarg1, Location jarg1_, int jarg2);
  public final static native long Location_mapLocation(long jarg1, Location jarg1_);
  public final static native boolean Location_isInGarrison(long jarg1, Location jarg1_);
  public final static native int Location_structure(long jarg1, Location jarg1_);
  public final static native boolean Location_isInSpace(long jarg1, Location jarg1_);
  public final static native boolean Location_isAdjacentTo(long jarg1, Location jarg1_, long jarg2, Location jarg2_);
  public final static native boolean Location_isWithinRange(long jarg1, Location jarg1_, long jarg2, long jarg3, Location jarg3_);
  public final static native String Location_toString(long jarg1, Location jarg1_);
  public final static native long Location_clone(long jarg1, Location jarg1_);
  public final static native boolean Location_equals(long jarg1, Location jarg1_, long jarg2, Location jarg2_);
  public final static native String Location_toJson(long jarg1, Location jarg1_);
  public final static native long bcLocationNewOnMap(long jarg1, MapLocation jarg1_);
  public final static native long bcLocationNewInGarrison(int jarg1);
  public final static native long bcLocationNewInSpace();
  public final static native long bcLocationFromJson(String jarg1);
  public final static native int bcTeamFromJson(String jarg1);
  public final static native String bcTeamToJson(int jarg1);
  public final static native long new_Player(int jarg1, int jarg2);
  public final static native void delete_Player(long jarg1);
  public final static native String Player_toString(long jarg1, Player jarg1_);
  public final static native long Player_clone(long jarg1, Player jarg1_);
  public final static native boolean Player_equals(long jarg1, Player jarg1_, long jarg2, Player jarg2_);
  public final static native String Player_toJson(long jarg1, Player jarg1_);
  public final static native void Player_team_set(long jarg1, Player jarg1_, int jarg2);
  public final static native int Player_team_get(long jarg1, Player jarg1_);
  public final static native void Player_planet_set(long jarg1, Player jarg1_, int jarg2);
  public final static native int Player_planet_get(long jarg1, Player jarg1_);
  public final static native long bcPlayerFromJson(String jarg1);
  public final static native long new_VecUnitID();
  public final static native void delete_VecUnitID(long jarg1);
  public final static native String VecUnitID_toString(long jarg1, VecUnitID jarg1_);
  public final static native long VecUnitID_clone(long jarg1, VecUnitID jarg1_);
  public final static native long VecUnitID_size(long jarg1, VecUnitID jarg1_);
  public final static native int VecUnitID_get(long jarg1, VecUnitID jarg1_, long jarg2);
  public final static native int bcUnitTypeFromJson(String jarg1);
  public final static native String bcUnitTypeToJson(int jarg1);
  public final static native long bcUnitTypeFactoryCost(int jarg1);
  public final static native long bcUnitTypeBlueprintCost(int jarg1);
  public final static native long bcUnitTypeReplicateCost(int jarg1);
  public final static native long bcUnitTypeValue(int jarg1);
  public final static native long new_VecUnitType();
  public final static native void delete_VecUnitType(long jarg1);
  public final static native String VecUnitType_toString(long jarg1, VecUnitType jarg1_);
  public final static native long VecUnitType_clone(long jarg1, VecUnitType jarg1_);
  public final static native long VecUnitType_size(long jarg1, VecUnitType jarg1_);
  public final static native int VecUnitType_get(long jarg1, VecUnitType jarg1_, long jarg2);
  public final static native long new_Unit();
  public final static native void delete_Unit(long jarg1);
  public final static native String Unit_toString(long jarg1, Unit jarg1_);
  public final static native long Unit_clone(long jarg1, Unit jarg1_);
  public final static native String Unit_toJson(long jarg1, Unit jarg1_);
  public final static native boolean Unit_equals(long jarg1, Unit jarg1_, long jarg2, Unit jarg2_);
  public final static native int Unit_id(long jarg1, Unit jarg1_);
  public final static native int Unit_team(long jarg1, Unit jarg1_);
  public final static native long Unit_researchLevel(long jarg1, Unit jarg1_);
  public final static native int Unit_unitType(long jarg1, Unit jarg1_);
  public final static native long Unit_location(long jarg1, Unit jarg1_);
  public final static native long Unit_health(long jarg1, Unit jarg1_);
  public final static native long Unit_maxHealth(long jarg1, Unit jarg1_);
  public final static native long Unit_visionRange(long jarg1, Unit jarg1_);
  public final static native int Unit_damage(long jarg1, Unit jarg1_);
  public final static native long Unit_attackRange(long jarg1, Unit jarg1_);
  public final static native long Unit_movementHeat(long jarg1, Unit jarg1_);
  public final static native long Unit_attackHeat(long jarg1, Unit jarg1_);
  public final static native long Unit_movementCooldown(long jarg1, Unit jarg1_);
  public final static native long Unit_attackCooldown(long jarg1, Unit jarg1_);
  public final static native short Unit_isAbilityUnlocked(long jarg1, Unit jarg1_);
  public final static native long Unit_abilityHeat(long jarg1, Unit jarg1_);
  public final static native long Unit_abilityCooldown(long jarg1, Unit jarg1_);
  public final static native long Unit_abilityRange(long jarg1, Unit jarg1_);
  public final static native short Unit_workerHasActed(long jarg1, Unit jarg1_);
  public final static native long Unit_workerBuildHealth(long jarg1, Unit jarg1_);
  public final static native long Unit_workerRepairHealth(long jarg1, Unit jarg1_);
  public final static native long Unit_workerHarvestAmount(long jarg1, Unit jarg1_);
  public final static native long Unit_knightDefense(long jarg1, Unit jarg1_);
  public final static native long Unit_rangerCannotAttackRange(long jarg1, Unit jarg1_);
  public final static native long Unit_rangerMaxCountdown(long jarg1, Unit jarg1_);
  public final static native short Unit_rangerIsSniping(long jarg1, Unit jarg1_);
  public final static native long Unit_rangerTargetLocation(long jarg1, Unit jarg1_);
  public final static native long Unit_rangerCountdown(long jarg1, Unit jarg1_);
  public final static native long Unit_healerSelfHealAmount(long jarg1, Unit jarg1_);
  public final static native short Unit_structureIsBuilt(long jarg1, Unit jarg1_);
  public final static native long Unit_structureMaxCapacity(long jarg1, Unit jarg1_);
  public final static native long Unit_structureGarrison(long jarg1, Unit jarg1_);
  public final static native short Unit_isFactoryProducing(long jarg1, Unit jarg1_);
  public final static native int Unit_factoryUnitType(long jarg1, Unit jarg1_);
  public final static native long Unit_factoryRoundsLeft(long jarg1, Unit jarg1_);
  public final static native long Unit_factoryMaxRoundsLeft(long jarg1, Unit jarg1_);
  public final static native short Unit_rocketIsUsed(long jarg1, Unit jarg1_);
  public final static native int Unit_rocketBlastDamage(long jarg1, Unit jarg1_);
  public final static native long Unit_rocketTravelTimeDecrease(long jarg1, Unit jarg1_);
  public final static native long bcUnitFromJson(String jarg1);
  public final static native long new_VecUnit();
  public final static native void delete_VecUnit(long jarg1);
  public final static native String VecUnit_toString(long jarg1, VecUnit jarg1_);
  public final static native long VecUnit_clone(long jarg1, VecUnit jarg1_);
  public final static native long VecUnit_size(long jarg1, VecUnit jarg1_);
  public final static native long VecUnit_get(long jarg1, VecUnit jarg1_, long jarg2);
  public final static native long new_PlanetMap();
  public final static native void delete_PlanetMap(long jarg1);
  public final static native boolean PlanetMap_validate(long jarg1, PlanetMap jarg1_);
  public final static native boolean PlanetMap_onMap(long jarg1, PlanetMap jarg1_, long jarg2, MapLocation jarg2_);
  public final static native short PlanetMap_isPassableTerrainAt(long jarg1, PlanetMap jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long PlanetMap_initialKarboniteAt(long jarg1, PlanetMap jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long PlanetMap_clone(long jarg1, PlanetMap jarg1_);
  public final static native String PlanetMap_toJson(long jarg1, PlanetMap jarg1_);
  public final static native void PlanetMap_planet_set(long jarg1, PlanetMap jarg1_, int jarg2);
  public final static native int PlanetMap_planet_get(long jarg1, PlanetMap jarg1_);
  public final static native void PlanetMap_height_set(long jarg1, PlanetMap jarg1_, long jarg2);
  public final static native long PlanetMap_height_get(long jarg1, PlanetMap jarg1_);
  public final static native void PlanetMap_width_set(long jarg1, PlanetMap jarg1_, long jarg2);
  public final static native long PlanetMap_width_get(long jarg1, PlanetMap jarg1_);
  public final static native void PlanetMap_initial_units_set(long jarg1, PlanetMap jarg1_, long jarg2, VecUnit jarg2_);
  public final static native long PlanetMap_initial_units_get(long jarg1, PlanetMap jarg1_);
  public final static native long bcPlanetMapFromJson(String jarg1);
  public final static native long new_Delta();
  public final static native void delete_Delta(long jarg1);
  public final static native String Delta_toJson(long jarg1, Delta jarg1_);
  public final static native long bcDeltaFromJson(String jarg1);
  public final static native long new_StartGameMessage();
  public final static native void delete_StartGameMessage(long jarg1);
  public final static native String StartGameMessage_toJson(long jarg1, StartGameMessage jarg1_);
  public final static native long bcStartGameMessageFromJson(String jarg1);
  public final static native long new_TurnMessage();
  public final static native void delete_TurnMessage(long jarg1);
  public final static native String TurnMessage_toJson(long jarg1, TurnMessage jarg1_);
  public final static native long bcTurnMessageFromJson(String jarg1);
  public final static native long new_StartTurnMessage();
  public final static native void delete_StartTurnMessage(long jarg1);
  public final static native String StartTurnMessage_toJson(long jarg1, StartTurnMessage jarg1_);
  public final static native void StartTurnMessage_time_left_ms_set(long jarg1, StartTurnMessage jarg1_, int jarg2);
  public final static native int StartTurnMessage_time_left_ms_get(long jarg1, StartTurnMessage jarg1_);
  public final static native void StartTurnMessage_round_set(long jarg1, StartTurnMessage jarg1_, long jarg2);
  public final static native long StartTurnMessage_round_get(long jarg1, StartTurnMessage jarg1_);
  public final static native long bcStartTurnMessageFromJson(String jarg1);
  public final static native long new_ViewerMessage();
  public final static native void delete_ViewerMessage(long jarg1);
  public final static native String ViewerMessage_toJson(long jarg1, ViewerMessage jarg1_);
  public final static native long bcViewerMessageFromJson(String jarg1);
  public final static native long new_ViewerKeyframe();
  public final static native void delete_ViewerKeyframe(long jarg1);
  public final static native String ViewerKeyframe_toJson(long jarg1, ViewerKeyframe jarg1_);
  public final static native long bcViewerKeyframeFromJson(String jarg1);
  public final static native long new_ErrorMessage();
  public final static native void delete_ErrorMessage(long jarg1);
  public final static native String ErrorMessage_toJson(long jarg1, ErrorMessage jarg1_);
  public final static native String ErrorMessage_toString(long jarg1, ErrorMessage jarg1_);
  public final static native void ErrorMessage_error_set(long jarg1, ErrorMessage jarg1_, String jarg2);
  public final static native String ErrorMessage_error_get(long jarg1, ErrorMessage jarg1_);
  public final static native long bcErrorMessageFromJson(String jarg1);
  public final static native long new_ReceivedMessaTurnMessage();
  public final static native void delete_ReceivedMessaTurnMessage(long jarg1);
  public final static native String ReceivedMessaTurnMessage_toJson(long jarg1, ReceivedMessaTurnMessage jarg1_);
  public final static native String ReceivedMessaTurnMessage_toString(long jarg1, ReceivedMessaTurnMessage jarg1_);
  public final static native long bcReceivedMessaTurnMessageFromJson(String jarg1);
  public final static native long new_SentMessage();
  public final static native void delete_SentMessage(long jarg1);
  public final static native String SentMessage_toJson(long jarg1, SentMessage jarg1_);
  public final static native String SentMessage_toString(long jarg1, SentMessage jarg1_);
  public final static native void SentMessage_client_id_set(long jarg1, SentMessage jarg1_, String jarg2);
  public final static native String SentMessage_client_id_get(long jarg1, SentMessage jarg1_);
  public final static native void SentMessage_turn_message_set(long jarg1, SentMessage jarg1_, long jarg2, TurnMessage jarg2_);
  public final static native long SentMessage_turn_message_get(long jarg1, SentMessage jarg1_);
  public final static native long bcSentMessageFromJson(String jarg1);
  public final static native long new_TurnApplication();
  public final static native void delete_TurnApplication(long jarg1);
  public final static native void TurnApplication_start_turn_set(long jarg1, TurnApplication jarg1_, long jarg2, StartTurnMessage jarg2_);
  public final static native long TurnApplication_start_turn_get(long jarg1, TurnApplication jarg1_);
  public final static native void TurnApplication_start_turn_error_set(long jarg1, TurnApplication jarg1_, int jarg2);
  public final static native int TurnApplication_start_turn_error_get(long jarg1, TurnApplication jarg1_);
  public final static native void TurnApplication_viewer_set(long jarg1, TurnApplication jarg1_, long jarg2, ViewerMessage jarg2_);
  public final static native long TurnApplication_viewer_get(long jarg1, TurnApplication jarg1_);
  public final static native long new_InitialTurnApplication();
  public final static native void delete_InitialTurnApplication(long jarg1);
  public final static native void InitialTurnApplication_start_turn_set(long jarg1, InitialTurnApplication jarg1_, long jarg2, StartTurnMessage jarg2_);
  public final static native long InitialTurnApplication_start_turn_get(long jarg1, InitialTurnApplication jarg1_);
  public final static native void InitialTurnApplication_viewer_set(long jarg1, InitialTurnApplication jarg1_, long jarg2, ViewerKeyframe jarg2_);
  public final static native long InitialTurnApplication_viewer_get(long jarg1, InitialTurnApplication jarg1_);
  public final static native long new_AsteroidStrike(long jarg1, long jarg2, MapLocation jarg2_);
  public final static native void delete_AsteroidStrike(long jarg1);
  public final static native long AsteroidStrike_clone(long jarg1, AsteroidStrike jarg1_);
  public final static native String AsteroidStrike_toString(long jarg1, AsteroidStrike jarg1_);
  public final static native String AsteroidStrike_toJson(long jarg1, AsteroidStrike jarg1_);
  public final static native boolean AsteroidStrike_equals(long jarg1, AsteroidStrike jarg1_, long jarg2, AsteroidStrike jarg2_);
  public final static native void AsteroidStrike_karbonite_set(long jarg1, AsteroidStrike jarg1_, long jarg2);
  public final static native long AsteroidStrike_karbonite_get(long jarg1, AsteroidStrike jarg1_);
  public final static native void AsteroidStrike_location_set(long jarg1, AsteroidStrike jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long AsteroidStrike_location_get(long jarg1, AsteroidStrike jarg1_);
  public final static native long bcAsteroidStrikeFromJson(String jarg1);
  public final static native long new_AsteroidPattern(int jarg1, long jarg2, PlanetMap jarg2_);
  public final static native void delete_AsteroidPattern(long jarg1);
  public final static native boolean AsteroidPattern_validate(long jarg1, AsteroidPattern jarg1_);
  public final static native boolean AsteroidPattern_hasAsteroid(long jarg1, AsteroidPattern jarg1_, long jarg2);
  public final static native long AsteroidPattern_asteroid(long jarg1, AsteroidPattern jarg1_, long jarg2);
  public final static native long AsteroidPattern_clone(long jarg1, AsteroidPattern jarg1_);
  public final static native String AsteroidPattern_toString(long jarg1, AsteroidPattern jarg1_);
  public final static native String AsteroidPattern_toJson(long jarg1, AsteroidPattern jarg1_);
  public final static native long bcAsteroidPatternFromJson(String jarg1);
  public final static native long new_OrbitPattern(long jarg1, long jarg2, long jarg3);
  public final static native void delete_OrbitPattern(long jarg1);
  public final static native boolean OrbitPattern_validate(long jarg1, OrbitPattern jarg1_);
  public final static native long OrbitPattern_duration(long jarg1, OrbitPattern jarg1_, long jarg2);
  public final static native String OrbitPattern_toJson(long jarg1, OrbitPattern jarg1_);
  public final static native void OrbitPattern_amplitude_set(long jarg1, OrbitPattern jarg1_, long jarg2);
  public final static native long OrbitPattern_amplitude_get(long jarg1, OrbitPattern jarg1_);
  public final static native void OrbitPattern_period_set(long jarg1, OrbitPattern jarg1_, long jarg2);
  public final static native long OrbitPattern_period_get(long jarg1, OrbitPattern jarg1_);
  public final static native void OrbitPattern_center_set(long jarg1, OrbitPattern jarg1_, long jarg2);
  public final static native long OrbitPattern_center_get(long jarg1, OrbitPattern jarg1_);
  public final static native long bcOrbitPatternFromJson(String jarg1);
  public final static native long new_GameMap();
  public final static native void delete_GameMap(long jarg1);
  public final static native void GameMap_validate(long jarg1, GameMap jarg1_);
  public final static native long GameMap_clone(long jarg1, GameMap jarg1_);
  public final static native String GameMap_toJson(long jarg1, GameMap jarg1_);
  public final static native void GameMap_seed_set(long jarg1, GameMap jarg1_, int jarg2);
  public final static native int GameMap_seed_get(long jarg1, GameMap jarg1_);
  public final static native void GameMap_earth_map_set(long jarg1, GameMap jarg1_, long jarg2, PlanetMap jarg2_);
  public final static native long GameMap_earth_map_get(long jarg1, GameMap jarg1_);
  public final static native void GameMap_mars_map_set(long jarg1, GameMap jarg1_, long jarg2, PlanetMap jarg2_);
  public final static native long GameMap_mars_map_get(long jarg1, GameMap jarg1_);
  public final static native void GameMap_asteroids_set(long jarg1, GameMap jarg1_, long jarg2, AsteroidPattern jarg2_);
  public final static native long GameMap_asteroids_get(long jarg1, GameMap jarg1_);
  public final static native void GameMap_orbit_set(long jarg1, GameMap jarg1_, long jarg2, OrbitPattern jarg2_);
  public final static native long GameMap_orbit_get(long jarg1, GameMap jarg1_);
  public final static native long bcGameMapTestMap();
  public final static native long bcGameMapParseTextMap(String jarg1);
  public final static native long bcGameMapFromJson(String jarg1);
  public final static native long maxLevel(int jarg1);
  public final static native long costOf(int jarg1, long jarg2);
  public final static native long new_ResearchInfo();
  public final static native void delete_ResearchInfo(long jarg1);
  public final static native long ResearchInfo_getLevel(long jarg1, ResearchInfo jarg1_, int jarg2);
  public final static native long ResearchInfo_queue(long jarg1, ResearchInfo jarg1_);
  public final static native boolean ResearchInfo_hasNextInQueue(long jarg1, ResearchInfo jarg1_);
  public final static native int ResearchInfo_nextInQueue(long jarg1, ResearchInfo jarg1_);
  public final static native long ResearchInfo_roundsLeft(long jarg1, ResearchInfo jarg1_);
  public final static native String ResearchInfo_toJson(long jarg1, ResearchInfo jarg1_);
  public final static native long bcResearchInfoFromJson(String jarg1);
  public final static native long new_RocketLanding(int jarg1, long jarg2, MapLocation jarg2_);
  public final static native void delete_RocketLanding(long jarg1);
  public final static native long RocketLanding_clone(long jarg1, RocketLanding jarg1_);
  public final static native String RocketLanding_toString(long jarg1, RocketLanding jarg1_);
  public final static native String RocketLanding_toJson(long jarg1, RocketLanding jarg1_);
  public final static native boolean RocketLanding_equals(long jarg1, RocketLanding jarg1_, long jarg2, RocketLanding jarg2_);
  public final static native void RocketLanding_rocket_id_set(long jarg1, RocketLanding jarg1_, int jarg2);
  public final static native int RocketLanding_rocket_id_get(long jarg1, RocketLanding jarg1_);
  public final static native void RocketLanding_destination_set(long jarg1, RocketLanding jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long RocketLanding_destination_get(long jarg1, RocketLanding jarg1_);
  public final static native long bcRocketLandingFromJson(String jarg1);
  public final static native long new_VecRocketLanding();
  public final static native void delete_VecRocketLanding(long jarg1);
  public final static native String VecRocketLanding_toString(long jarg1, VecRocketLanding jarg1_);
  public final static native long VecRocketLanding_clone(long jarg1, VecRocketLanding jarg1_);
  public final static native long VecRocketLanding_size(long jarg1, VecRocketLanding jarg1_);
  public final static native long VecRocketLanding_get(long jarg1, VecRocketLanding jarg1_, long jarg2);
  public final static native long new_RocketLandingInfo();
  public final static native void delete_RocketLandingInfo(long jarg1);
  public final static native long RocketLandingInfo_landingsOn(long jarg1, RocketLandingInfo jarg1_, long jarg2);
  public final static native long RocketLandingInfo_clone(long jarg1, RocketLandingInfo jarg1_);
  public final static native String RocketLandingInfo_toString(long jarg1, RocketLandingInfo jarg1_);
  public final static native String RocketLandingInfo_toJson(long jarg1, RocketLandingInfo jarg1_);
  public final static native boolean RocketLandingInfo_equals(long jarg1, RocketLandingInfo jarg1_, long jarg2, RocketLandingInfo jarg2_);
  public final static native long bcRocketLandingInfoFromJson(String jarg1);
  public final static native long new_GameController();
  public final static native void delete_GameController(long jarg1);
  public final static native void GameController_nextTurn(long jarg1, GameController jarg1_);
  public final static native int GameController_getTimeLeftMs(long jarg1, GameController jarg1_);
  public final static native long GameController_round(long jarg1, GameController jarg1_);
  public final static native int GameController_planet(long jarg1, GameController jarg1_);
  public final static native int GameController_team(long jarg1, GameController jarg1_);
  public final static native long GameController_startingMap(long jarg1, GameController jarg1_, int jarg2);
  public final static native long GameController_karbonite(long jarg1, GameController jarg1_);
  public final static native long GameController_unit(long jarg1, GameController jarg1_, int jarg2);
  public final static native long GameController_units(long jarg1, GameController jarg1_);
  public final static native long GameController_myUnits(long jarg1, GameController jarg1_);
  public final static native long GameController_unitsInSpace(long jarg1, GameController jarg1_);
  public final static native long GameController_karboniteAt(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long GameController_allLocationsWithin(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_, long jarg3);
  public final static native boolean GameController_canSenseLocation(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_);
  public final static native boolean GameController_canSenseUnit(long jarg1, GameController jarg1_, int jarg2);
  public final static native long GameController_senseNearbyUnits(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_, long jarg3);
  public final static native long GameController_senseNearbyUnitsByTeam(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_, long jarg3, int jarg4);
  public final static native long GameController_senseNearbyUnitsByType(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_, long jarg3, int jarg4);
  public final static native boolean GameController_hasUnitAtLocation(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long GameController_senseUnitAtLocation(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_);
  public final static native long GameController_asteroidPattern(long jarg1, GameController jarg1_);
  public final static native long GameController_orbitPattern(long jarg1, GameController jarg1_);
  public final static native long GameController_currentDurationOfFlight(long jarg1, GameController jarg1_);
  public final static native long GameController_getTeamArray(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_writeTeamArray(long jarg1, GameController jarg1_, long jarg2, int jarg3);
  public final static native void GameController_disintegrateUnit(long jarg1, GameController jarg1_, int jarg2);
  public final static native short GameController_isOccupiable(long jarg1, GameController jarg1_, long jarg2, MapLocation jarg2_);
  public final static native boolean GameController_canMove(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_isMoveReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_moveRobot(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canAttack(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_isAttackReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_attack(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native long GameController_researchInfo(long jarg1, GameController jarg1_);
  public final static native short GameController_resetResearch(long jarg1, GameController jarg1_);
  public final static native short GameController_queueResearch(long jarg1, GameController jarg1_, int jarg2);
  public final static native boolean GameController_canHarvest(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_harvest(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canBlueprint(long jarg1, GameController jarg1_, int jarg2, int jarg3, int jarg4);
  public final static native void GameController_blueprint(long jarg1, GameController jarg1_, int jarg2, int jarg3, int jarg4);
  public final static native boolean GameController_canBuild(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_build(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canRepair(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_repair(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canReplicate(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_replicate(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canJavelin(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_isJavelinReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_javelin(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canBeginSnipe(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native boolean GameController_isBeginSnipeReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_beginSnipe(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native boolean GameController_canBlink(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native boolean GameController_isBlinkReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_blink(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native boolean GameController_canHeal(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_isHealReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_heal(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canOvercharge(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_isOverchargeReady(long jarg1, GameController jarg1_, int jarg2);
  public final static native void GameController_overcharge(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canLoad(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_load(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canUnload(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_unload(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native boolean GameController_canProduceRobot(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native void GameController_produceRobot(long jarg1, GameController jarg1_, int jarg2, int jarg3);
  public final static native long GameController_rocketLandings(long jarg1, GameController jarg1_);
  public final static native boolean GameController_canLaunchRocket(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native void GameController_launchRocket(long jarg1, GameController jarg1_, int jarg2, long jarg3, MapLocation jarg3_);
  public final static native long GameController_startGame(long jarg1, GameController jarg1_, long jarg2, Player jarg2_);
  public final static native long GameController_applyTurn(long jarg1, GameController jarg1_, long jarg2, TurnMessage jarg2_, int jarg3);
  public final static native long GameController_initialStartTurnMessage(long jarg1, GameController jarg1_, int jarg2);
  public final static native boolean GameController_isOver(long jarg1, GameController jarg1_);
  public final static native int GameController_winningTeam(long jarg1, GameController jarg1_);
  public final static native String GameController_managerViewerMessage(long jarg1, GameController jarg1_);
  public final static native void GameController_printGameAnsi(long jarg1, GameController jarg1_);
  public final static native long GameController_managerKarbonite(long jarg1, GameController jarg1_, int jarg2);
  public final static native long bcGameControllerNewManager(long jarg1, GameMap jarg1_);
}
