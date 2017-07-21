/**
 * IPathserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class IPathserviceLocator extends org.apache.axis.client.Service implements org.tempuri.IPathservice {

    public IPathserviceLocator() {
    }


    public IPathserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IPathserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IPathPort
    private java.lang.String IPathPort_address = "http://10.28.2.126/soap/IPath";

    public java.lang.String getIPathPortAddress() {
        return IPathPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IPathPortWSDDServiceName = "IPathPort";

    public java.lang.String getIPathPortWSDDServiceName() {
        return IPathPortWSDDServiceName;
    }

    public void setIPathPortWSDDServiceName(java.lang.String name) {
        IPathPortWSDDServiceName = name;
    }

    public org.tempuri.IPath getIPathPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IPathPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIPathPort(endpoint);
    }

    public org.tempuri.IPath getIPathPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.IPathbindingStub _stub = new org.tempuri.IPathbindingStub(portAddress, this);
            _stub.setPortName(getIPathPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIPathPortEndpointAddress(java.lang.String address) {
        IPathPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IPath.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.IPathbindingStub _stub = new org.tempuri.IPathbindingStub(new java.net.URL(IPathPort_address), this);
                _stub.setPortName(getIPathPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IPathPort".equals(inputPortName)) {
            return getIPathPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IPathservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "IPathPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IPathPort".equals(portName)) {
            setIPathPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
