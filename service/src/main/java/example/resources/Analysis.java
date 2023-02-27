package example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("analysis")
public class Analysis {

    @GET
    @Path("{baselinePodHash}/{canaryPodHash}")
    public Response runAnalysis(@PathParam("baselinePodHash") String baselinePodHash,
                                @PathParam("canaryPodHash") String canaryPodHash) {

        String response = "{\"data\": {\"ok\": true, \"successPercent\": 0.95, \"baselinePodHash\": \"%s\", \"canaryPodHash\": \"%s\"}}";
        return Response.ok(String.format(response, baselinePodHash, canaryPodHash)).build();
    }
}
