package org.os.flume;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WriteLog {
	//protected static final Log logger = LogFactory.getLog(WriteLog.class);  
	protected static final Logger logger = LoggerFactory.getLogger(WriteLog.class);
    /** 
     * @param args 
     * @throws InterruptedException  
     */  
    public static void main(String[] args) throws InterruptedException {  
        // TODO Auto-generated method stub  
    	int i = 0;
        while (true) {  
        //每隔两秒log输出一下当前系统时间戳  
        	Long d = new Date().getTime();
        	System.out.println(i ++ );
        	logger.info(String.valueOf(i));  
/*            if (i%300 == 0)
            	Thread.sleep(5000);  */
            if (i == 300) {
            	return;
            }
        }  
    }  
}
