package org.tempuri;

public class IPathProxy implements org.tempuri.IPath {
  private String _endpoint = null;
  private org.tempuri.IPath iPath = null;
  
  public IPathProxy() {
    _initIPathProxy();
  }
  
  public IPathProxy(String endpoint) {
    _endpoint = endpoint;
    _initIPathProxy();
  }
  
  private void _initIPathProxy() {
    try {
      iPath = (new org.tempuri.IPathserviceLocator()).getIPathPort();
      if (iPath != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iPath)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iPath)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iPath != null)
      ((javax.xml.rpc.Stub)iPath)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IPath getIPath() {
    if (iPath == null)
      _initIPathProxy();
    return iPath;
  }
  
  public java.lang.String test(java.lang.String a) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    return iPath.test(a);
  }
  
  public void sendResultInfo(java.lang.String picID, java.lang.String picURL, java.lang.String picExtName, java.lang.String aPicture, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendResultInfo(picID, picURL, picExtName, aPicture, msg, _return);
  }
  
  
}