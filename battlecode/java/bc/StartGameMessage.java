/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class StartGameMessage {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected StartGameMessage(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StartGameMessage obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_StartGameMessage(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public StartGameMessage() {
    this(bcJNI.new_StartGameMessage(), true);
  }

  public String toJson() {
    return bcJNI.StartGameMessage_toJson(swigCPtr, this);
  }

}
