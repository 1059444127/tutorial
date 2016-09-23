package sample.cluster.transformation;

import java.util.Scanner;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class TestMain {

	public static void main(String[] args) {
		  // Override the configuration of the port when specified as program argument
	    final String port = args.length > 0 ? args[0] : "0";
	    final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
	      withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]")).
	      withFallback(ConfigFactory.load());

	    ActorSystem system = ActorSystem.create("ClusterSystem", config);

	    ActorRef testActor = system.actorOf(Props.create(Test.class), "backend");
	    
	    Scanner in = new Scanner(System.in);
	    while (in.hasNextLine()) {
	    	String s = in.nextLine();
	    	System.out.println("......................." + s);
	    	testActor.tell(s, testActor);
	    }
	}

}
