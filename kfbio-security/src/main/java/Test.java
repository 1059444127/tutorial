import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;


public class Test {

	public static void main(String[] args) throws Exception{
		
		Token token = new Token();
		token.setIp("192.168.0.37");
		token.setUsername("xxx");
		token.setPassword("xxx");
		
		String jsonString = JSON.toJSONString(token);
		System.out.println(jsonString);
		
		
		Base64 base64=new Base64();  

		byte[] data = jsonString.getBytes("UTF-8");
		//byte[] key=new BASE64Decoder().decodeBuffer("kfbviewer1234567kfbviewer1234567");
		byte[] key = base64.decode("kfbviewer1234567kfbviewer1234567".getBytes("UTF-8"));
		byte[] enc = des3EncodeECB(key, data);
		
        byte[] b=base64.encode(enc);  
        String s=new String(b);
        System.out.println("UncToken="+s);
		
	}
	

    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }
    

}
