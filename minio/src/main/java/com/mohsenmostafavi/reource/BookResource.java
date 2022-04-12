package com.mohsenmostafavi.reource;

import com.mohsenmostafavi.reource.service.FileService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequestScoped
@Path("/person")
public class BookResource {

    @Inject
    FileService service;


    @POST
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@MultipartForm MultipartFormDataInput input) {
        try {
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

            List<InputPart> inputParts = uploadForm.get("file");
            for (InputPart inputPart : inputParts) {
                try {

                    InputStream inputStream = inputPart.getBody(InputStream.class, null);
                    service.createFile(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
