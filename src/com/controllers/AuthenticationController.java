package com.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.ejbs.AuthenticationRemote;
import com.entities.Utilisateur;
import models.JsonResult;
import models.UtilisationToken;
import security.JwtSecurity;

@Stateless
@Path("/auth")
public class AuthenticationController {
	
	@EJB(lookup ="ejb:/HotelBookersEJB//Authentication!com.ejbs.AuthenticationRemote")
    private AuthenticationRemote authenticationRemote;
	private JwtSecurity secur=new JwtSecurity();

	@Path("/register")
	@POST
    @Produces("application/json")
	public JsonResult register(@FormParam("nom")String nom,@FormParam("prenom")String prenom,@FormParam("adresse")String adresse,@FormParam("ville")String ville,@FormParam("region")String region,@FormParam("codePostal")String codePostal,@FormParam("sexe")String sexe,@FormParam("numTel")String numTel,@FormParam("email")String email,@FormParam("password")String pwd,@FormParam("role")String role ) {
		
		
		if(authenticationRemote.registerUser(nom,prenom,adresse,ville,region,codePostal,sexe,numTel,email,pwd,role)) {
			return new JsonResult(201,"inscription reussi");
		}
		return new JsonResult(401, "email deja utilise, veuillez vous authentifier !");
	}
	
//	@Path("/loginGet/{username}/{password}")
//	@GET
//    @Produces("application/json") //for test only 
//	public JsonResult loginGet(@PathParam("username") String username,@PathParam("password") String password ) {
// 
//		Utilisateur util=authenticationRemote.validUser(username,password);
//		if(util!=null) {
//			return new JsonResult(0, new UtilisationToken(util, secur.createToken("Authentication", util.getId().toString()))) ; 			
//		}
//		else {
//			return new JsonResult(401, "user not register! please register");
//		}
//		
//	}
	
	@Path("/login")
	@POST
    @Produces("application/json")
	public JsonResult login(@FormParam("email") String username,@FormParam("password") String password ) {
		Utilisateur util=authenticationRemote.validUser(username,password);
		if(util!=null) {
			return new JsonResult(201, new UtilisationToken(util, secur.createToken("Authentication", util.getId().toString()))) ; 			
		}
		else {
			return new JsonResult(401, "user not register! please register");
		}
				
	}
	
	
}
