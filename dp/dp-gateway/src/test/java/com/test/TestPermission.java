package com.test;

import java.util.HashMap;
import java.util.Map;

public class TestPermission {

	@org.junit.Test
	public void testPermission(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", "common");
		map.put("password", "123456");
		/*测试时去掉下面的注释*/
//		String cookie = HttpRequstService.postMethod("http://localhost:8080/dp/dp/login", map);
//		System.out.println("cookie:"+cookie);
//		String test = HttpRequstService.sendGetUp("http://localhost:8080/dp/dp/test",cookie);
//		System.out.println("test:"+test);
//		String result = HttpRequstService.postMethod("http://localhost:8080/dp/dp/slide",new HashMap(),cookie);
//		System.out.println("没有权限！异常信息：【");
//		System.out.println(result);
//		System.out.println("】");
	}
	  
}  

