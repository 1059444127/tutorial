package cn.myapps.webservice.client;
import cn.myapps.webservice.client.DocumentService;
import cn.myapps.webservice.client.DocumentServiceProxy;
import cn.myapps.webservice.model.SimpleDocument;

public class Test {

	public static void main(String[] args) throws Exception{
		System.out.println("....................");
		DocumentService ds = new DocumentServiceProxy();
		String domainUserId="11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
		String applicationId="11e4-868e-476f6c83-afc0-bf7d9fc712b0";
		
		SimpleDocument sd = ds.searchDocumentByFilter("未诊断列表", "\"ci_request_id\": \"125669516\"", applicationId, domainUserId);
		System.out.println(sd.toString());

	}

}
