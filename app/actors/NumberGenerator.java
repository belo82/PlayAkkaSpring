package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.util.Duration;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Akka;
import play.libs.Json;
import play.mvc.WebSocket;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Actor responsible for generating and pushing numbers.
 *
 * @author Peter Belko
 */
public class NumberGenerator extends UntypedActor {

    private Random rnd = new Random();
    private boolean pushing = false;


    // this class representing an actor functionality



    // connected players
    // private Map<String, WebSocket.Out<JsonNode>> members = new HashMap<String, WebSocket.Out<JsonNode>>();
    private List<WebSocket.Out<JsonNode>> players = new ArrayList<WebSocket.Out<JsonNode>>();


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof RegisterPlayer) {
            RegisterPlayer mssg = (RegisterPlayer) message;

            players.add(mssg.getChannel());

            if (!pushing) {
                Akka.system().scheduler().schedule(
                        Duration.create(10, TimeUnit.SECONDS),
                        Duration.create(10, TimeUnit.SECONDS),
                        getSelf(),
                        new PushNumber());
            }

        } else if (message instanceof PushNumber) {
            PushNumber mssg = (PushNumber) message;
            notifyAll(rnd.nextInt(90)+1);

        } else {
            unhandled(message);
        }
    }

    private void notifyAll(int number) {
        ObjectNode on = Json.newObject();
        on.put("number", number);

        for (WebSocket.Out<JsonNode> ws : players) {
            ws.write(on);
        }
    }


    // -- MESSAGES --//

    public static class RegisterPlayer {

        private WebSocket.Out<JsonNode> channel;

        public RegisterPlayer(WebSocket.Out<JsonNode> channel) {
            this.channel = channel;
        }

        public WebSocket.Out<JsonNode> getChannel() {
            return channel;
        }
    }

    public static class PushNumber {
        // empty class
    }
}
