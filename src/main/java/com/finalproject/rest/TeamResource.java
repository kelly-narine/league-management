package com.finalproject.rest;

import com.finalproject.entity.Player;
import com.finalproject.entity.PlayerUpdate;
import com.finalproject.entity.Team;
import com.finalproject.entity.TeamUpdate;
import com.finalproject.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Path("/teams")
public class TeamResource {
    @EJB
    private TeamService teamService;

    //request to check if the service is working
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Team Service is working").build();
    }

    //request to create a team
    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Team team) {
        teamService.createTeam(team);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    //request to get the list of teams
    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllTeams() {
        return Response.ok().entity(teamService.getTeamList()).build();
    }


    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateTeam(TeamUpdate updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Team teamToUpdate = teamService.getById(updateDto.getId());
        if (teamToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(teamService.updateTeam(updateDto, teamToUpdate)).build();
    }
}
