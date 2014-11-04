package hazelcast.startstop.core;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class TestService implements MessageListener<String> {

  private String name;
  private ITopic<String> commandTopic;
  private boolean serviceIsRunning = false;

  public TestService() {
    this.name = "service-" + System.nanoTime();

    final HazelcastInstance instance = Hazelcast.newHazelcastInstance();
    commandTopic = instance.getTopic("commands");
    commandTopic.addMessageListener(this);
  }

  ///////////////////////////////////////////
/*  public static void main(String ... args) {
    final String instanceName = args[0];
    final TestService testService = new TestService(instanceName);
    System.out.println("Service " + instanceName + " created");

    while(true) {}
  }*/
  ///////////////////////////////////////////

  public void controlService(String command) {
    if (command.equalsIgnoreCase("start")) {
      start();
    }
    else if (command.equalsIgnoreCase("stop")) {
      stop();
    }
    else {
      System.out.println("Invalid command specified");
    }
  }

  @Override
  public void onMessage(Message<String> messageString) {
    final String message = messageString.getMessageObject();
    controlService(message);
  }

  public boolean isServiceIsRunning() {
    return serviceIsRunning;
  }

  private void setServiceIsRunning(boolean value) {
    serviceIsRunning = value;
  }

  private void start() {
    if (!isServiceIsRunning()) {
      System.out.println("Starting " + name);
      setServiceIsRunning(true);

      commandTopic.publish("start");
    }
  }

  private void stop() {
    if (isServiceIsRunning()) {
      System.out.println("Stopping " + name);
      setServiceIsRunning(false);

      commandTopic.publish("stop");
    }
  }

}
