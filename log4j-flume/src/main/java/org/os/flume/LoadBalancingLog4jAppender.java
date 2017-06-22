package org.os.flume;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.FlumeException;
import org.apache.flume.api.RpcClientConfigurationConstants;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.api.RpcClientFactory.ClientType;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class LoadBalancingLog4jAppender extends Log4jAppender {


	  private String hosts;
	  private String selector;
	  private String maxBackoff;
	  private boolean configured = false;
	  private String maxIoWorkers;

	  public void setHosts(String hostNames) {
	    this.hosts = hostNames;
	  }

	  public void setSelector(String selector) {
	    this.selector = selector;
	  }

	  public void setMaxBackoff(String maxBackoff) {
	    this.maxBackoff = maxBackoff;
	  }

	  @Override
	  public synchronized void append(LoggingEvent event) {
	    if (!configured) {
	      String errorMsg = "Flume Log4jAppender not configured correctly! Cannot" +
	          " send events to Flume.";
	      LogLog.error(errorMsg);
	      if (getUnsafeMode()) {
	        return;
	      }
	      throw new FlumeException(errorMsg);
	    }
	    super.append(event);
	  }

	  /**
	   * Activate the options set using <tt>setHosts()</tt>, <tt>setSelector</tt>
	   * and <tt>setMaxBackoff</tt>
	   *
	   * @throws FlumeException
	   *           if the LoadBalancingRpcClient cannot be instantiated.
	   */
	  @Override
	  public void activateOptions() throws FlumeException {
	    try {
	      final Properties properties = getProperties(hosts, selector, maxBackoff, getTimeout());
	      rpcClient = RpcClientFactory.getInstance(properties);
	      if (layout != null) {
	        layout.activateOptions();
	      }
	      configured = true;
	    } catch (Exception e) {
	      String errormsg = "RPC client creation failed! " + e.getMessage();
	      LogLog.error(errormsg);
	      if (getUnsafeMode()) {
	        return;
	      }
	      throw new FlumeException(e);
	    }

	  }

	  private Properties getProperties(String hosts, String selector,
	      String maxBackoff, long timeout) throws FlumeException {

	    if (StringUtils.isEmpty(hosts)) {
	      throw new FlumeException("hosts must not be null");
	    }

	    Properties props = new Properties();
	    String[] hostsAndPorts = hosts.split("\\s+");
	    StringBuilder names = new StringBuilder();
	    for (int i = 0; i < hostsAndPorts.length; i++) {
	      String hostAndPort = hostsAndPorts[i];
	      String name = "h" + i;
	      props.setProperty(RpcClientConfigurationConstants.CONFIG_HOSTS_PREFIX + name,
	          hostAndPort);
	      names.append(name).append(" ");
	    }
	    props.put(RpcClientConfigurationConstants.CONFIG_HOSTS, names.toString());
	    props.put(RpcClientConfigurationConstants.CONFIG_CLIENT_TYPE,
	        ClientType.DEFAULT_LOADBALANCE.toString());
	    if (!StringUtils.isEmpty(selector)) {
	      props.put(RpcClientConfigurationConstants.CONFIG_HOST_SELECTOR, selector);
	    }

	    if (!StringUtils.isEmpty(maxBackoff)) {
	      long millis = Long.parseLong(maxBackoff.trim());
	      if (millis <= 0) {
	        throw new FlumeException(
	            "Misconfigured max backoff, value must be greater than 0");
	      }
	      props.put(RpcClientConfigurationConstants.CONFIG_BACKOFF, String.valueOf(true));
	      props.put(RpcClientConfigurationConstants.CONFIG_MAX_BACKOFF, maxBackoff);
	    }
	    props.setProperty(RpcClientConfigurationConstants.CONFIG_CONNECT_TIMEOUT,
	                      String.valueOf(timeout));
	    props.setProperty(RpcClientConfigurationConstants.CONFIG_REQUEST_TIMEOUT,
	                      String.valueOf(timeout));
	    
	    props.setProperty(RpcClientConfigurationConstants.MAX_IO_WORKERS, "30");
	    return props;
	  }
}
