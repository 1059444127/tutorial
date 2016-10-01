import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	private MiniDFSCluster cluster;
	private FileSystem fs;

	@Before
	public void setUp() throws IOException {
		Configuration conf = new Configuration();
		if (System.getProperty("test/build/data") == null) {
			System.setProperty("test/build/data", "e:/hadoop-book/tmp");
		}
		cluster = new MiniDFSCluster.Builder(conf).build();
		fs = cluster.getFileSystem();
		OutputStream out = fs.create(new Path("/dir/file"));
		out.write("content".getBytes("UTF-8"));
		out.close();
	}
	
	@Test
	public void echo(){
		System.out.println("hello world");
	}
}
