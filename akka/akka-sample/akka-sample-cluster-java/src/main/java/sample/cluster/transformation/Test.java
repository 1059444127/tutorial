package sample.cluster.transformation;

import static sample.cluster.transformation.TransformationMessages.BACKEND_REGISTRATION;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.CurrentClusterState;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.Member;
import akka.cluster.MemberStatus;
import sample.cluster.transformation.TransformationMessages.TransformationJob;
import sample.cluster.transformation.TransformationMessages.TransformationResult;

public class Test extends UntypedActor {

	Cluster cluster = Cluster.get(getContext().system());

	private ActorSelection front;
	
	  //subscribe to cluster changes, MemberUp
	  @Override
	  public void preStart() {
	    cluster.subscribe(getSelf(), MemberUp.class);
	  }

	  //re-subscribe when restart
	  @Override
	  public void postStop() {
	    cluster.unsubscribe(getSelf());
	  }

	  @Override
	  public void onReceive(Object message) {
	    if (message instanceof TransformationJob) {
	      TransformationJob job = (TransformationJob) message;
	      getSender().tell(new TransformationResult("JUST TEST"),
	          getSelf());

	    } else if (message instanceof CurrentClusterState) {
	      CurrentClusterState state = (CurrentClusterState) message;
	      for (Member member : state.getMembers()) {
	        if (member.status().equals(MemberStatus.up())) {
	          register(member);
	        }
	      }

	    } else if (message instanceof MemberUp) {
	      MemberUp mUp = (MemberUp) message;
	      register(mUp.member());

	    } else {
	    	System.out.println("unhandled Test received " + message);
	    	front.tell(message, getSelf());
	      unhandled(message);
	    }
	  }

	  void register(Member member) {
	    if (member.hasRole("frontend")){
	    	front = getContext().actorSelection(member.address() + "/user/frontend");
	    	front.tell(
		    		  "xxxxxxxxxxxxxxxxxx", getSelf());
	      getContext().actorSelection(member.address() + "/user/frontend").tell(
	    		  BACKEND_REGISTRATION, getSelf());
	    }
	  }

}
