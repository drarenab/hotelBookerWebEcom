package com.controllers;

import javax.ejb.EJB;
//import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ejbs.AdministrationRemote;
import com.ejbs.AuthenticationRemote;
import com.entities.Utilisateur;
import com.models.JsonResult;
import com.security.JwtSecurity;

@Stateless
@Path("/administration")
public class AdministrationController {
	@EJB(lookup = "ejb:/HotelBookersEJB/Administration!com.ejbs.AdministrationRemote")//change
	private AdministrationRemote administrationRemote;
	
	@EJB(lookup = "ejb:/HotelBookersEJB/Authentication!com.ejbs.AuthenticationRemote")//change
	private AuthenticationRemote authenticationRemote;
	
	private JwtSecurity secur = new JwtSecurity();

	
	@POST
	@Path("/etatChange")
	@Produces("application/json")//dont use refresh token now
	public JsonResult modifyEtatChambre(@FormParam("token") String token,@DefaultValue("-1") @FormParam("idChambre") Long idChambre) {
		//verifier champs non vide
		if(token==null||token.isEmpty()|| idChambre==-1)
			return new JsonResult(401, "Tous les champs doivent etre renseigner");
		//valider le token
		String idUser=secur.validateToken(token);
		
		if(idUser==null || idUser.isEmpty())
			return new JsonResult(401, "user not connected!");
		Utilisateur u=authenticationRemote.getUserFromId(Long.parseLong(idUser));
		if(!u.getRole().getlibelle().equals("ROLE_ADMIN"))
			return new JsonResult(401, "Vous n'avez pas le droit d'executer cette action");
		//modifer
		administrationRemote.modifyEtatChambre(idChambre);
		return new JsonResult(201, "Modification de l'etat de la chambre reussi");
	}
}
