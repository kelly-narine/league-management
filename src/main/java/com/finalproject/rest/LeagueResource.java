package com.finalproject.rest;

import com.finalproject.entity.League;
import com.finalproject.entity.Team;
import com.finalproject.service.LeagueService;
import com.finalproject.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/league")
public class LeagueResource {

    @EJB
    private LeagueService leagueService;

    //request to check if the service is working or not
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("League Service is working").build();
    }

    //request to create league
    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createLeague(League league) {
        leagueService.createLeague(league);
        return Response.status(Response.Status.CREATED).entity(league).build();
    }

    //request to get leagues
    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllLeagues() {
        return Response.ok().entity(leagueService.getLeagueist()).build();
    }
}
