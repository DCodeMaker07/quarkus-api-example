package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/temperaturas")
public class TemperaturasResource {

    private final TemperaturasService temperaturasService;

    @Inject
    public TemperaturasResource(TemperaturasService temperaturasService) {
        this.temperaturasService = temperaturasService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura nueva(Temperatura temp) {
        temperaturasService.addTemperatura(temp);
        return temp;
    }

    @GET
    public List<Temperatura> list() {
        return temperaturasService.getAllTemperaturas();
    }

    @GET
    @Path("/maxima")
    @Produces(MediaType.TEXT_PLAIN)
    public Response maxima() {
        if(temperaturasService.isEmpty()) {
            return Response.status(404).entity("No hay temperaturas").build();
        } else {
            int temperaturaMaxima = temperaturasService.maxima();
            return Response.ok(Integer.toString(temperaturaMaxima))
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @GET
    @Path("/{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura sacar(@PathParam("ciudad") String ciudad) {
        return this.temperaturasService.sacarTemperatura(ciudad)
                .orElseThrow(() -> new NoSuchElementException("No hay registro para la ciudad" + ciudad));
    }

//    @GET
//    @Produces("text/plain")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/una")
//    public Temperatura medicion() {
//        return new Temperatura("Málaga", 18, 28);
//    }

}
