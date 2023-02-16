package example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("hello")
public class Hello {

    @GET
    @Path("{text}")
    public Response displayHelloText(@PathParam("text") String text) {
        return Response.ok(String.format("Hello %s! Good afternoon!!!", text)).build();
    }
}
