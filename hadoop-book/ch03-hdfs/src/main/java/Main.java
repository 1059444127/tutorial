import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;

public class Main {

	public static void main(String[] args) throws IOException {
		MiniDFSCluster cluster ;
		FileSystem fs;
		
		Configuration conf = new Configuration();
		if(System.getProperty("test/build/data") == null) {
			System.setProperty("test/build/data", "/tmp");
		}
		cluster = new MiniDFSCluster.Builder(conf).build();
		fs = cluster.getFileSystem();
		OutputStream out = fs.create(new Path("/dir/file"));
		out.write("content".getBytes("UTF-8"));
		out.close();
	}

}
