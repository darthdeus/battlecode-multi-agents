/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class VecUnitID {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected VecUnitID(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(VecUnitID obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_VecUnitID(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public VecUnitID() {
    this(bcJNI.new_VecUnitID(), true);
  }

  public String toString() {
    return bcJNI.VecUnitID_toString(swigCPtr, this);
  }

  public VecUnitID clone() {
    long cPtr = bcJNI.VecUnitID_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new VecUnitID(cPtr, true);
  }

  public long size() {
    return bcJNI.VecUnitID_size(swigCPtr, this);
  }

  public int get(long index) {
    return bcJNI.VecUnitID_get(swigCPtr, this, index);
  }

}
