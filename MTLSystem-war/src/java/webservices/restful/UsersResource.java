/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.restful;

import entity.MTLUser;
import error.GeneralException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.UserSessionLocal;

/**
 * REST Web Service
 *
 * @author Kian Wee
 */
@Path("users")
public class UsersResource {
    
    @EJB
    private UserSessionLocal userSessionLocal;
    
    // SYSTEM FUNCTIONS BELOW **************************************************************    
    
    // System function - userLogin
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {
            MTLUser u = userSessionLocal.userLogin(username, password);
            return Response.status(200).entity(
                    u
            ).type(MediaType.APPLICATION_JSON).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end getUser
    
    // System function - searchUsers
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUsers(@QueryParam("username") String username,
            @QueryParam("phoneNumber") int phoneNumber,
            @QueryParam("email") String email) throws GeneralException {
        if (username != null) {
            List<MTLUser> results
                    = userSessionLocal.searchUsers(username);
            GenericEntity<List<MTLUser>> entity = new GenericEntity<List<MTLUser>>(results) {
            };
            return Response.status(200).entity(
                    entity
            ).build();
        } else if (phoneNumber > 0) {
            List<MTLUser> results
                    = userSessionLocal.searchUsersByPhoneNumber(phoneNumber);
            GenericEntity<List<MTLUser>> entity = new GenericEntity<List<MTLUser>>(results) {
            };
            return Response.status(200).entity(
                    entity
            ).build();
        } else if (email != null) {
            List<MTLUser> results
                    = userSessionLocal.searchUsersByEmail(email);
            GenericEntity<List<MTLUser>> entity = new GenericEntity<List<MTLUser>>(results) {
            };
            return Response.status(200).entity(
                    entity
            ).build();
        } else {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "No query conditions")
                    .build();
            return Response.status(400).entity(exception).build();
        }
    } //end searchUsers
    
    // System function - getUser
    @GET
    @Path("/uId={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long uId) {
        try {
            MTLUser u = userSessionLocal.getUser(uId);
            return Response.status(200).entity(
                    u
            ).type(MediaType.APPLICATION_JSON).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end getUser
    
    // System function - createUser
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MTLUser createUser(MTLUser u) {
        u.setUserCreated(new Date());
        userSessionLocal.createUser(u);
        return u;
    } //end createUser
    
    // System function - updateUser
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(MTLUser u) {
        try {
            userSessionLocal.updateUser(u);
            return Response.status(204).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end updateUser
    
    // ADMIN FUNCTIONS BELOW **************************************************************
    
    // Admin function - viewAllUsers
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MTLUser> viewAllUsers() {
        return userSessionLocal.viewAllUsers();
    } //end viewAllUsers 
    
    // Admin function - activateUser
    @PUT
    @Path("/activateUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateUser(@PathParam("id") Long uId) {
        try {
            userSessionLocal.activateUser(uId);
            return Response.status(204).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end activateUser
    
    // Admin function - deactivateUser
    @PUT
    @Path("/deactivateUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deactivateUser(@PathParam("id") Long uId) {
        try {
            userSessionLocal.deactivateUser(uId);
            return Response.status(204).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end deactivateUser

    // Admin function - Only for testing use
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long uId) {
        try {
            userSessionLocal.deleteUser(uId);
            return Response.status(204).build();
        } catch (GeneralException e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "User Not Found")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } //end deleteUser
    
    // SELLER FUNCTIONS BELOW **************************************************************
    
    
}
