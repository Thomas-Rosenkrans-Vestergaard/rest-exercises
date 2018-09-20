package com.tvestergaard.rest.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception>
{

    static private Gson           gson = new GsonBuilder().setPrettyPrinting().create();
    @Context       ServletContext servletContext;

    @Override public Response toResponse(Exception exception)
    {
        boolean      isDebug      = "true".equals(servletContext.getInitParameter("debug"));
        ErrorMessage errorMessage = new ErrorMessage("An exception occurred.", 500, isDebug, exception);

        return Response.status(500)
                       .entity(gson.toJson(errorMessage))
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .build();
    }
}
