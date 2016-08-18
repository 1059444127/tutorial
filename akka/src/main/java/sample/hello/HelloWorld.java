package sample.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {
	
	@Override
	public void preStart() throws Exception {
		ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		greeter.tell(Greeter.MSG.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg == Greeter.MSG.DONE) {
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}

	}

}
