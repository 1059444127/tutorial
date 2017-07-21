/**
 * IPathservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IPathservice extends javax.xml.rpc.Service {
    public java.lang.String getIPathPortAddress();

    public org.tempuri.IPath getIPathPort() throws javax.xml.rpc.ServiceException;

    public org.tempuri.IPath getIPathPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
