package example.resources;

import com.google.common.base.Strings;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

@Path("host")
public class Host {

    private int currentEntry = 0;
    private boolean lockFile = false;
    private final int maxRetry = 3;

    @GET
    @Path("entry")
    @Produces({MediaType.APPLICATION_JSON })
    public Response roundRobinEntry() throws Exception {
        HashMap<String, String> result = new HashMap<>();
        int retry = 0;

        String entry = "";
        while (retry < maxRetry && Strings.isNullOrEmpty(entry)) {
            if (!lockFile) {
                entry = getFileEntry();
                currentEntry++;
            } else {
                wait(10);
                retry++;
            }
        }

        if (entry.indexOf(":") > 0) {
            result.put("URL", entry.substring(0, entry.indexOf(":")));
            result.put("PORT", entry.substring(entry.indexOf(":") + 1));
        } else {
            throw new RuntimeException("Entry is not properly formatted");
        }
        return Response.ok(result).build();
    }

    private String getFileEntry() {
        FileInputStream stream;
        try {
            stream = new FileInputStream("service/src/main/resources/config/configuration.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }

        lockFile = true;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            int i = 0;
            int limit = currentEntry % 3; // reader.size();
            // 1. limit = 0 % 3 = 0
            // 2. limit = 1 % 3 = 1
            // 3. limit = 2 % 3 = 2
            // 4. limit = 3 % 3 = 0

            while (i < limit) {
                reader.readLine();
                i++;
            }
            return reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException("IOException");
        } finally {
            lockFile = false;
        }
    }
}
