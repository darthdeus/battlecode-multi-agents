/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package bc;

public class SentMessage {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SentMessage(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SentMessage obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        bcJNI.delete_SentMessage(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SentMessage() {
    this(bcJNI.new_SentMessage(), true);
  }

  public String toJson() {
    return bcJNI.SentMessage_toJson(swigCPtr, this);
  }

  public String toString() {
    return bcJNI.SentMessage_toString(swigCPtr, this);
  }

  public void setClient_id(String value) {
    bcJNI.SentMessage_client_id_set(swigCPtr, this, value);
  }

  public String getClient_id() {
    return bcJNI.SentMessage_client_id_get(swigCPtr, this);
  }

  public void setTurn_message(TurnMessage value) {
    bcJNI.SentMessage_turn_message_set(swigCPtr, this, TurnMessage.getCPtr(value), value);
  }

  public TurnMessage getTurn_message() {
    long cPtr = bcJNI.SentMessage_turn_message_get(swigCPtr, this);
    return (cPtr == 0) ? null : new TurnMessage(cPtr, false);
  }

}
