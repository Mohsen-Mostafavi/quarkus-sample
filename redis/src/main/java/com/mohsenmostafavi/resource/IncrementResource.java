package com.mohsenmostafavi.resource;

import com.mohsenmostafavi.entity.Increment;
import com.mohsenmostafavi.service.IncrementService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/increments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IncrementResource {
    @Inject
    IncrementService service;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    @POST
    public Response create(Increment increment) {
        try {
            service.set(increment.getKey(), increment.getValue());
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{key}")
    public Response get(@PathParam("key") String key) {
        try {
            return Response.ok().entity(new Increment(key, Integer.parseInt(service.get(key)))).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{key}")
    public Response increment(@PathParam("key") String key, Integer value) {
        try {
            service.increment(key, value);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(@PathParam("key") String key) {
        return service.del(key);
    }
}
