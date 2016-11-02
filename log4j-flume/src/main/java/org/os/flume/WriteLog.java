package org.os.flume;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WriteLog {
	protected static final Log logger = LogFactory.getLog(WriteLog.class);  
	  
    /** 
     * @param args 
     * @throws InterruptedException  
     */  
    public static void main(String[] args) throws InterruptedException {  
        // TODO Auto-generated method stub  
        while (true) {  
        //每隔两秒log输出一下当前系统时间戳  
            logger.info(new Date().getTime());  
            logger.info("Hello world");
            Thread.sleep(2000);  
        }  
    }  
}
