package controllers;

import actors.NumberGenerator;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Component;
import play.libs.Akka;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

/**
 * Default controller.
 *
 * @author Peter Belko
 */
@Component
public class Application extends Controller {

    private ActorRef numberGeneratorActor = Akka.system().actorOf(new Props(NumberGenerator.class));

    public Result index() {
        return ok("Hello World!");
    }

    public WebSocket<JsonNode> pushNumber() {
        return new WebSocket<JsonNode>() {
            @Override
            public void onReady(In<JsonNode> jsonNodeIn, Out<JsonNode> jsonNodeOut) {
                numberGeneratorActor.tell(new NumberGenerator.RegisterPlayer(jsonNodeOut));
            }

        };
    }

}