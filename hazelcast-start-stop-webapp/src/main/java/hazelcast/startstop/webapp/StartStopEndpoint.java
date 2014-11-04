package hazelcast.startstop.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hazelcast.startstop.core.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("command")
@Produces("text/plain")
public class StartStopEndpoint {

  @Autowired
  private TestService testService;

  @Path("/health")
  @GET
  public Response healthCheck() {
    return Response.ok().entity("Reached: healthcheck").build();
  }

  @Path("/start")
  @GET
  public Response startService() {
    testService.controlService("bogusStart");
    return Response.ok().build();
  }

  @Path("/stop")
  @GET
  public Response stopService() {
    testService.controlService("bogusStop");
    return Response.ok().build();
  }
}

