package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/temperaturas")
public class TemperaturasResource {

    private List<Temperatura> valores = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura nueva(Temperatura temp) {
        valores.add(temp);
        return temp;
    }

    @GET
    public List<Temperatura> list() {
        return Collections.unmodifiableList(valores);
    }

    @GET
//    @Produces("text/plain")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/una")
    public Temperatura medicion() {
        return new Temperatura("Málaga", 18, 28);
    }

}
