package com.mohsenmostafavi.resource;

import com.mohsenmostafavi.entity.Person;
import com.mohsenmostafavi.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    @Inject
    PersonService personService;

    @GET
    @Path("/list")
    public Response getPersons(){
        try {
            return Response.ok().entity(personService.findPersons()).build();
        }catch (Exception e){
            return Response.serverError().status(Response.Status.BAD_REQUEST).build();
        }
    }


    @GET
    @Path("/{nationalCode}")
    public Response getPersonByNationalCode(@PathParam("nationalCode") String nationalCode){
        try {
            return Response.ok().entity(personService.findPersonByNationalCode(nationalCode)).build();
        }catch (Exception e){
            return Response.serverError().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response getPersonByDetails(Person person){
        try {
            return Response.ok().entity(personService.findPersonByDetails(person)).build();
        }catch (Exception e){
            return Response.serverError().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    public Response create(Person person, @Context UriInfo uriInfo) {
        try {
            personService.create(person);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        try {
            personService.edit(person);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            personService.delete(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
