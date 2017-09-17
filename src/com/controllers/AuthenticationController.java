package com.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ejbs.AuthenticationRemote;
import com.entities.Utilisateur;

@Stateless
@Path("/auth")
public class AuthenticationController {

	@EJB(lookup ="ejb:/HotelBookersEJB//Authentication!com.ejbs.AuthenticationRemote")
    private AuthenticationRemote authenticationRemote;
	
	@Path("/register")
	@GET
    @Produces("application/json")
	public void register() {
		
	}
	@Path("/loginGet/{username}/{password}")
	@GET
    @Produces("application/json") //for test only 
	public Utilisateur loginGet(@PathParam("username") String username,@PathParam("password") String password ) {

		return authenticationRemote.validUser(username,password);
		
	}
	@Path("/login")
	@POST
    @Produces("application/json")
	public Utilisateur login(@FormParam("username") String username,@FormParam("password") String password ) {
		return authenticationRemote.validUser(username,password);
		
	}
	
	
}
