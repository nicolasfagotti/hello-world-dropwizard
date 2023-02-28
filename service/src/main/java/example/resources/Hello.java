package example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("hello")
public class Hello {

    @GET
    @Path("{text}")
    public Response displayHelloText(@PathParam("text") String text) {
        return Response.ok(String.format("Hello %s! Good afternoon!!!", text)).build();
    }

    @Path("/status")
    @GET
    public Response getStatus(@QueryParam("statusResponse") Integer statusResponse) {
        statusResponse = statusResponse != null ? statusResponse : 200;
        return Response.status(statusResponse, String.format("Status returned: %d", statusResponse)).build();
    }
}
