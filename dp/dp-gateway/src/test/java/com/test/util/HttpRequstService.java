package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


public class HttpRequstService {
	
	/**
	 * 登陆返回sessionId
	 * @param url 登陆地址
	 * @param map  用户名密码
	 * @return   cookie
	 */
	public static String postMethod(String url,Map<String,Object> map){
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
        post.setRequestBody(param);
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
        post.setRequestHeader("cookie", cookies);    
        HttpClient client = new HttpClient();
        try {
        	client.executeMethod(post);
			// 打印出返回数据，检验一下是否成功  
            result = post.getResponseBodyAsString(); 
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        post.releaseConnection();
        return result;
    }
	
	/**
	 * get请求
	 * 使用登陆cookie进行  登陆后操作
	 * @param url
	 * @param strCookie
	 * @return
	 */
	public static String sendGetUp(String url,String strCookie) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("cookie", strCookie);
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
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
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
    
}
