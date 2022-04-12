package com.mohsenmostafavi.reource;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class MultiPartBody {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

}
