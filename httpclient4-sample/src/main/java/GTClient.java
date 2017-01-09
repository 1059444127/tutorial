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

import com.google.gson.Gson;

public class GTClient {

	public final static void main(String[] args) throws Exception {
        testPost();
    }

	public static void testPost() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	//String path = "http://10.5.5.119:8080/post";
        	String path = "http://10.63.8.5:11111/GTtService.svc/slideReady";
            HttpPost httppost = new HttpPost(path);
            httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
            String content = "";
            
            /*
            content = "{"
            					+ "\"barcode\":\"P0038_GD001_01_HE\", "
            					+ "\"url\": \"http://10.63.8.211:8989/UploadService/Slides/05bdc9f0-35dc-4458-b9bc-b58092b89f22?gurl=&token=b8dcf672bc887bc23bf3628b8ea9c209&ts=3059090980499\", "
            					+ "\"viewer\": \"motic\", "
            					+ "\"thumbnail\": \"\""
            				+ "}";
            */
            Gson gson = new Gson();
            SlideReadyMsg sre= new SlideReadyMsg();
            //sre.setBarcode("P0038_GD001_01_HE");
            //sre.setUrl("http://10.63.8.5:11111/UploadService/Slides/05bdc9f0-35dc-4458-b9bc-b58092b89f22?gurl=&token=b8dcf672bc887bc23bf3628b8ea9c209&ts=3059090980499");
            
            String barcode="JXZ131020_GD001_1_HE";
            String url="http://10.63.8.211:8989/UploadService/Slides/Barcode/JXZ131020_GD001_1_HE?token=f3141621574fbf2922b830722d32f7ef&ts=3059710206061";
            String thumbnail="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";
            sre.setViewer("motic");
            sre.setBarcode(barcode);
            sre.setUrl(url);
            sre.setThumbnail(thumbnail);
            content = gson.toJson(sre);
            System.out.println(content);
            
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
