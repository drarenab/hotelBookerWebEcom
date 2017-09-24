package com.controllers;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.models.JsonResult;
import com.utilities.EmailSender;

@Stateless
@Path("/contact")
public class ContactController {

	@POST
	@Path("/contactUs")
	@Produces("application/json")
	public JsonResult contactUs(@FormParam("email") String email,@FormParam("nom") String nom,@FormParam("sujet") String sujet,@FormParam("message") String message) {
		if(email.isEmpty()||nom.isEmpty()||sujet.isEmpty()||message.isEmpty())
				return new JsonResult(401, "Tous les champs sont obligatoire, veuille a bien les renseigner");
		
		String [] to= {"abdelkarim.drareni@gmail.com"};

		String [] cc= {"benomarseifeddine@gmail.com","ghemid_m_dz@yahoo.fr"};
		String msg = "<p style=\"text-align: center;\"><span style=\"color: #ff0000; font-size: 14pt;\">Cher administrateur Pyramid,</span></p>\n" + 
				"<p>Un nouveau message vous a &eacute;tait envoyer.</p>\n" + 
				"<p><strong>Nom de l'exp&eacute;diteur ;</strong>"+ nom +" </p>\n" + 
				"<p><strong>Email de l'exp&eacute;diteur ;</strong> "+ email +"</p>\n" + 
				"<p><strong>Sujet:</strong>"+sujet+"</p>\n" + 
				"<p><strong>Message :</strong> "+message+"</p>\n" + 
				"<p>&nbsp;</p>\n" + 
				"<p>Bonne journ&eacute;e.</p>\n" + 
				"<div id=\"_rc_sig\">&nbsp;</div>";
		
		try {
			EmailSender.sendMail(sujet,msg,to ,cc );
			return new JsonResult(201,"Email envoyer, l'equipe PYRAMIDE vous remercie");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JsonResult(401,"Erreur lors de l'envoi du mail");

	}
	
}
