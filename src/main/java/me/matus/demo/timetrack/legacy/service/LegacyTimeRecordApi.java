/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.matus.demo.timetrack.legacy.service;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import me.matus.demo.timetrack.frontend.model.TimeRecordDTO;
import org.jboss.resteasy.annotations.Form;

/**
 * API for legacy system
 *
 * @author Matus_Majchrak
 */
@Path("/records")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface LegacyTimeRecordApi {

    @GET
    @Path("/")
    public List<TimeRecordDTO> fetchRecords(@QueryParam("email") String email, @QueryParam("offset") Long offset, @QueryParam("length") Long length);

    @POST
    @Path("/")
    public TimeRecordDTO createRecord(@FormParam("email") String email, @FormParam("start") String start, @FormParam("end") String end);
}
