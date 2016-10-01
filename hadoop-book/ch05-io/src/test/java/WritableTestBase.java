import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;

public class WritableTestBase {
	public static byte[] serialize(Writable writable) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);
		writable.write(dataOut);
		dataOut.close();
		return out.toByteArray();
	}
	
	public static byte[] deserialize(Writable writable, byte[] bytes) throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		DataInputStream dataInput = new DataInputStream(input);
		writable.readFields(dataInput);
		dataInput.close();
		return bytes;
	}
	
	public static String serializeToString(Writable writable) throws IOException {
		return StringUtils.byteToHexString(serialize(writable));
	}
	
	public static String writeTo(Writable src, Writable dst) throws IOException {
		byte[] data = deserialize(dst, serialize(src));
		return StringUtils.byteToHexString(data);
	}
}
