package me.matus.demo.timetrack.frontend.rest;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import me.matus.demo.timetrack.frontend.exception.ServiceException;
import org.slf4j.Logger;

/**
 * Transform any {@link ServiceException} thrown during invocation of REST resources to a useful JSON representation for the client.
 *
 * @author Matus Majchrak
 *
 */
@Provider
public class RSExceptionMapper implements ExceptionMapper<ServiceException> {

    @Inject
    private Logger log;
    
    @Override
    public Response toResponse(ServiceException exception) {
        log.error("JAXRS exception mapper: ", exception);
        Map<String, String> model = new HashMap<>();
        model.put("code", exception.getMessage());
        return Response.serverError().entity(model).build();
    }
}
