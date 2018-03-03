/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class AsteroidPattern {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected AsteroidPattern(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(AsteroidPattern obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_AsteroidPattern(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public AsteroidPattern(int seed, PlanetMap mars_map) {
    this(bcJNI.new_AsteroidPattern(seed, PlanetMap.getCPtr(mars_map), mars_map), true);
  }

  public boolean validate() {
    return bcJNI.AsteroidPattern_validate(swigCPtr, this);
  }

  public boolean hasAsteroid(long round) {
    return bcJNI.AsteroidPattern_hasAsteroid(swigCPtr, this, round);
  }

  public AsteroidStrike asteroid(long round) {
    long cPtr = bcJNI.AsteroidPattern_asteroid(swigCPtr, this, round);
    return (cPtr == 0) ? null : new AsteroidStrike(cPtr, true);
  }

  public AsteroidPattern clone() {
    long cPtr = bcJNI.AsteroidPattern_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new AsteroidPattern(cPtr, true);
  }

  public String toString() {
    return bcJNI.AsteroidPattern_toString(swigCPtr, this);
  }

  public String toJson() {
    return bcJNI.AsteroidPattern_toJson(swigCPtr, this);
  }

}
