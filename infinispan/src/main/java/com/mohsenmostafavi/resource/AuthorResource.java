package com.mohsenmostafavi.resource;

import com.mohsenmostafavi.entity.DTO.AuthorDTO;
import com.mohsenmostafavi.service.AuthorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    @Inject
    AuthorService authorService;

    @GET
    public Response getAuthors(){
        try {
            return Response.ok().entity(authorService.getCacheNames()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") String id) {
        try {
            return Response.ok().entity(authorService.getAuthorById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    public Response createAuthor(AuthorDTO authorDTO){
        try {
            authorService.createAuthor(authorDTO);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
