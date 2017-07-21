package com.kingmed.dp.gateway.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class HttpRequest {
    
	private static Logger logger = Logger.getLogger(HttpRequest.class);
	
	/**
	 * 登陆返回sessionId
	 * @param url 登陆地址
	 * @param map  用户名密码
	 * @return   cookie
	 */
	public static String postMethodLogin(String url,Map<String,Object> map){
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
        NameValuePair[] param = new NameValuePair[map.size()];
        int i = 0;
		for( Map.Entry<String, Object> entry:map.entrySet()){
			param[i] = new NameValuePair(entry.getKey(),entry.getValue()+"");
			i++;
		}
        HttpMethodParams params = new HttpMethodParams();
        
        post.setParams(params);
        post.setRequestBody(param);
        StringBuffer tmpcookies = new StringBuffer("");  
        HttpClient client = new HttpClient();
        try {
        	 // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略  
        	client.getParams().setCookiePolicy(  
                    CookiePolicy.BROWSER_COMPATIBILITY); 
            
        	client.executeMethod(post);
			
			 // 获得登陆后的 Cookie  
            Cookie[] cookies = client.getState().getCookies();  
            
            for (Cookie c : cookies) {  
                tmpcookies.append(c.toString() + ";");  
            }  
            
		} catch (HttpException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
        post.releaseConnection();
        return tmpcookies.toString();
    }
	
	/**
	 * 通过登陆cookie  进行后续操作
	 * @param url
	 * @param map
	 * @param cookies
	 * @return
	 */
	public static String postMethod(String url,Map<String,Object> map,String cookies){
		String result = "";
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
        NameValuePair[] param = new NameValuePair[map.size()];
        int i = 0;
		for( Map.Entry<String, Object> entry:map.entrySet()){
			param[i] = new NameValuePair(entry.getKey(),entry.getValue()+"");
			i++;
		}
        HttpMethodParams params = new HttpMethodParams();
        
        post.setParams(params);
        post.setRequestBody(param);
        if(!"".equals(cookies)){
	        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
	        post.setRequestHeader("cookie", cookies);   
        }
        HttpClient client = new HttpClient();
        try {
        	client.executeMethod(post);
			// 打印出返回数据，检验一下是否成功  
            result = post.getResponseBodyAsString(); 
		} catch (HttpException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
        post.releaseConnection();
        return result;
    }
    
	 /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	String urlNameString = "";
        	if("".equals(param)){
        		urlNameString = url;
        	}else{
        		urlNameString = url + "?" + param;
        	}
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置 HttpURLConnection的字符编码
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送GET请求出现异常！url:" + url+"param:"+param,e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
    //**********************带认证的post请求***************************//
    public static String post(String strURL, Map<String,String> map,String username,String passwd) throws IOException {  
        System.out.println(strURL);  
        JSONObject params = JSONObject.fromObject(map);
        System.out.println(params.toString());  
        try {  
        	if(!"".equals(username)){
	        	//认证信息对象，用于包含访问翻译服务的用户名和密码    
	            Authenticator auth = new MyAuthenticator(username, passwd);    
	            Authenticator.setDefault(auth);   
        	}
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params.toString());  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                System.out.println(result);  
                return result;  
            }  
        } catch (IOException e) {  
            logger.error("post请求异常！"+strURL,e);
            throw e;
        }  
        return "error"; // 自定义错误信息  
    }  
}

/** 
 * powered by: testcs_dn 
 * Blog: http://blog.csdn.net/testcs_dn 
 * Create time: 2015/12/12 
 *  
 * 认证信息对象 
 */  
class MyAuthenticator extends Authenticator{   
      String _userName=null;   
      char[] _password=null;   
          
      public MyAuthenticator(){   
      }   
      public MyAuthenticator(String username, String password) {    
        this._userName = username;    
        this._password = password.toCharArray();    
      }    
      protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(_userName, _password);   
      }   
}
