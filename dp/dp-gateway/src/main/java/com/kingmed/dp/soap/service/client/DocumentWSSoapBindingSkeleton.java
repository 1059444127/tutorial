/**
 * DocumentWSSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kingmed.dp.soap.service.client;

public class DocumentWSSoapBindingSkeleton implements com.kingmed.dp.soap.service.client.DocumentWS, org.apache.axis.wsdl.Skeleton {
    private com.kingmed.dp.soap.service.client.DocumentWS impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "documentId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "domainUserId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "applicationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("updateDocumentByDomainUser", _params, new javax.xml.namespace.QName("", "createDocumentByDomainUserReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "updateDocumentByDomainUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("updateDocumentByDomainUser") == null) {
            _myOperations.put("updateDocumentByDomainUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("updateDocumentByDomainUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("DocumentWSFault");
        _fault.setQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "fault"));
        _fault.setClassName("com.kingmed.dp.soap.service.fault.DocumentWSFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.service.soap.dp.kingmed.com", "DocumentWSFault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "formName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "domainUserId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "applicationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createDocumentByDomainUser", _params, new javax.xml.namespace.QName("", "createDocumentByDomainUserReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "createDocumentByDomainUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createDocumentByDomainUser") == null) {
            _myOperations.put("createDocumentByDomainUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createDocumentByDomainUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("DocumentWSFault");
        _fault.setQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "fault"));
        _fault.setClassName("com.kingmed.dp.soap.service.fault.DocumentWSFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.service.soap.dp.kingmed.com", "DocumentWSFault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "caseParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "leaveParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sectionParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "domainUserId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "applicationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createDocumentByDomainUserV1", _params, new javax.xml.namespace.QName("", "createDocumentByDomainUserReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "createDocumentByDomainUserV1"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createDocumentByDomainUserV1") == null) {
            _myOperations.put("createDocumentByDomainUserV1", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createDocumentByDomainUserV1")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("DocumentWSFault");
        _fault.setQName(new javax.xml.namespace.QName("http://client.service.soap.dp.kingmed.com", "fault"));
        _fault.setClassName("com.kingmed.dp.soap.service.fault.DocumentWSFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.service.soap.dp.kingmed.com", "DocumentWSFault"));
        _oper.addFault(_fault);
    }

    public DocumentWSSoapBindingSkeleton() {
        this.impl = new com.kingmed.dp.soap.service.client.DocumentWSSoapBindingImpl();
    }

    public DocumentWSSoapBindingSkeleton(com.kingmed.dp.soap.service.client.DocumentWS impl) {
        this.impl = impl;
    }
    public int updateDocumentByDomainUser(java.lang.String documentId, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault
    {
        int ret = impl.updateDocumentByDomainUser(documentId, parameters, domainUserId, applicationId);
        return ret;
    }

    public int createDocumentByDomainUser(java.lang.String formName, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault
    {
        int ret = impl.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
        return ret;
    }

    public int createDocumentByDomainUserV1(java.util.HashMap caseParams, java.util.HashMap leaveParams, java.util.HashMap sectionParams, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault
    {
        int ret = impl.createDocumentByDomainUserV1(caseParams, leaveParams, sectionParams, domainUserId, applicationId);
        return ret;
    }

}
