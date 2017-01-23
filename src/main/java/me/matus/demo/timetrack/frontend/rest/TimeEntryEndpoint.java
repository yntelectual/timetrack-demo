/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.matus.demo.timetrack.frontend.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import me.matus.demo.timetrack.frontend.cdi.TracedExecution;
import me.matus.demo.timetrack.frontend.exception.ServiceException;
import me.matus.demo.timetrack.frontend.model.TimeRecordDTO;
import me.matus.demo.timetrack.legacy.service.LegacyTimeRecordClientService;

/**
 * Time entry rest endpoint.
 *
 * @author Matus_Majchrak
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@TracedExecution
@Path("/timeentry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimeEntryEndpoint {

    @Inject
    private LegacyTimeRecordClientService legacyClient;

    @GET
    @Path("/")
    public List<TimeRecordDTO> getTimeRecords(@QueryParam("offset") Long offset, @QueryParam("length") Long length, @QueryParam("email") String email) throws ServiceException {
        List<TimeRecordDTO> results = legacyClient.fetchRecordsFromLegacySystem(email, offset, length);
        return results;
    }

    @POST
    @Path("/")
    public TimeRecordDTO createNewEntry(TimeRecordDTO entry) throws ServiceException {
        TimeRecordDTO result = legacyClient.createNew(entry);
        return result;
    }
}
