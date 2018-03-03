/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class GameMap {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected GameMap(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(GameMap obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_GameMap(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public GameMap() {
    this(bcJNI.new_GameMap(), true);
  }

  public void validate() {
    bcJNI.GameMap_validate(swigCPtr, this);
  }

  public GameMap clone() {
    long cPtr = bcJNI.GameMap_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new GameMap(cPtr, true);
  }

  public String toJson() {
    return bcJNI.GameMap_toJson(swigCPtr, this);
  }

  public void setSeed(int value) {
    bcJNI.GameMap_seed_set(swigCPtr, this, value);
  }

  public int getSeed() {
    return bcJNI.GameMap_seed_get(swigCPtr, this);
  }

  public void setEarth_map(PlanetMap value) {
    bcJNI.GameMap_earth_map_set(swigCPtr, this, PlanetMap.getCPtr(value), value);
  }

  public PlanetMap getEarth_map() {
    long cPtr = bcJNI.GameMap_earth_map_get(swigCPtr, this);
    return (cPtr == 0) ? null : new PlanetMap(cPtr, false);
  }

  public void setMars_map(PlanetMap value) {
    bcJNI.GameMap_mars_map_set(swigCPtr, this, PlanetMap.getCPtr(value), value);
  }

  public PlanetMap getMars_map() {
    long cPtr = bcJNI.GameMap_mars_map_get(swigCPtr, this);
    return (cPtr == 0) ? null : new PlanetMap(cPtr, false);
  }

  public void setAsteroids(AsteroidPattern value) {
    bcJNI.GameMap_asteroids_set(swigCPtr, this, AsteroidPattern.getCPtr(value), value);
  }

  public AsteroidPattern getAsteroids() {
    long cPtr = bcJNI.GameMap_asteroids_get(swigCPtr, this);
    return (cPtr == 0) ? null : new AsteroidPattern(cPtr, false);
  }

  public void setOrbit(OrbitPattern value) {
    bcJNI.GameMap_orbit_set(swigCPtr, this, OrbitPattern.getCPtr(value), value);
  }

  public OrbitPattern getOrbit() {
    long cPtr = bcJNI.GameMap_orbit_get(swigCPtr, this);
    return (cPtr == 0) ? null : new OrbitPattern(cPtr, false);
  }

}
