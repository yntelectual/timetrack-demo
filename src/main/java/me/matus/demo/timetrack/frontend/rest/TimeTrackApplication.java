package me.matus.demo.timetrack.frontend.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAXRS application entry point. Specifies the root path for our rest endpoints.
 *
 * @author Matus_Majchrak
 */
@ApplicationPath("/rest/v1")
public class TimeTrackApplication extends Application {

}
