package com.finalproject.rest;

import com.finalproject.entity.Player;
import com.finalproject.entity.PlayerUpdate;
import com.finalproject.service.PlayerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/players")
public class PlayerResource {
    @EJB //handles transactions for us. Responsible for correct injection of Entity Manager and its instantiation
    private PlayerService playerService;

    //request to check if the service is working
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Player Service is working").build();
    }

    //request to delete player based on id
    @DELETE
    @Path("/{id}")
    public void deletePlayer(@PathParam("id") long id) {
        playerService.removeFromList(playerService.getById(id));
    }

    //request to get the list of players
    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers() {
        //TODO: Need to get the players by the team
        return Response.ok().entity(playerService.getPlayerList()).build();
    }

    //request to add a player
    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        playerService.addToList(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }

    //request to update a player
    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(PlayerUpdate updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Player playerToUpdate = playerService.getById(updateDto.getId());
        if (playerToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(playerService.updatePlayer(updateDto, playerToUpdate)).build();
    }

}
