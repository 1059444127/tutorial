import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GTClient {

	public final static void main(String[] args) throws Exception {
        testPost();
    }

	public static void testPost() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	String path = "http://localhost:8080/post";
            HttpPost httppost = new HttpPost(path);
            httppost.addHeader("Content-Type", "application/json;charset=UTF-8"); 
            String content = "{"
            					+ "'barcode':'', "
            					+ "'url': 'http://10.63.8.211:8989/UploadService/Slides/05bdc9f0-35dc-4458-b9bc-b58092b89f22?gurl=&token=b8dcf672bc887bc23bf3628b8ea9c209&ts=3059090980499', "
            					+ "'viewer': 'motic', "
            					+ "'thumbnail': ''"
            				+ "}";
            
            StringEntity entry = new StringEntity(content);
            httppost.setEntity(entry);

            System.out.println("Executing request: " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
	}
	
	public static void testGet() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://localhost:8080/");

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
	}

}
