package sample.hello;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

	public static enum MSG {
		GREET, DONE;
	}
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg == MSG.GREET) {
			System.out.println("Hello world!");
			getSender().tell(MSG.DONE, getSelf());
		} else {
			unhandled(msg);
		}

	}

}
