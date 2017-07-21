/**
 * IPath.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IPath extends java.rmi.Remote {
    public java.lang.String test(java.lang.String a) throws java.rmi.RemoteException;
    public void sendResultInfo(java.lang.String picID, java.lang.String picURL, java.lang.String picExtName, java.lang.String aPicture, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
}
