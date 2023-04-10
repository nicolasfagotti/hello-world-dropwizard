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

    @GET
    @Path("process/{serviceName}/score")
    public Response runGlosterAnalysis(@PathParam("serviceName") String serviceName) {

        String response = "{\"uid\":\"%s-20230410T185202Z-71057465\",\"running\":1,\"score\":0.98372940752667,\"confidence\":0.131628111773568,\"threshold\":0.9,\"serviceName\":\"%s\"}";
        return Response.ok(String.format(response, serviceName)).build();
    }
}
