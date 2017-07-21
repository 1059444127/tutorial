package com.kingmed.dp.soap.service.client;

public class DocumentWSProxy implements com.kingmed.dp.soap.service.client.DocumentWS {
  private String _endpoint = null;
  private com.kingmed.dp.soap.service.client.DocumentWS documentWS = null;
  
  public DocumentWSProxy() {
    _initDocumentWSProxy();
  }
  
  public DocumentWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initDocumentWSProxy();
  }
  
  private void _initDocumentWSProxy() {
    try {
      documentWS = (new com.kingmed.dp.soap.service.client.DocumentWSServiceLocator()).getDocumentWS();
      if (documentWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)documentWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)documentWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (documentWS != null)
      ((javax.xml.rpc.Stub)documentWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kingmed.dp.soap.service.client.DocumentWS getDocumentWS() {
    if (documentWS == null)
      _initDocumentWSProxy();
    return documentWS;
  }
  
  public int updateDocumentByDomainUser(java.lang.String documentId, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault{
    if (documentWS == null)
      _initDocumentWSProxy();
    return documentWS.updateDocumentByDomainUser(documentId, parameters, domainUserId, applicationId);
  }
  
  public int createDocumentByDomainUser(java.lang.String formName, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault{
    if (documentWS == null)
      _initDocumentWSProxy();
    return documentWS.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
  }
  
  public int createDocumentByDomainUserV1(java.util.HashMap caseParams, java.util.HashMap leaveParams, java.util.HashMap sectionParams, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault{
    if (documentWS == null)
      _initDocumentWSProxy();
    return documentWS.createDocumentByDomainUserV1(caseParams, leaveParams, sectionParams, domainUserId, applicationId);
  }
  
  
}