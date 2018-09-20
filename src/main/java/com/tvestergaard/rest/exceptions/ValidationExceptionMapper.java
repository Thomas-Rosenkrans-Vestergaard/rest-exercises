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
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>
{

    static private Gson           gson = new GsonBuilder().setPrettyPrinting().create();
    @Context       ServletContext servletContext;

    @Override public Response toResponse(ValidationException exception)
    {
        boolean      isDebug      = "true".equals(servletContext.getInitParameter("debug"));
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 400, isDebug, null);

        return Response.status(400)
                       .entity(gson.toJson(errorMessage))
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .build();
    }
}
