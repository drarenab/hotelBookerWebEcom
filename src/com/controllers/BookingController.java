package com.controllers;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ejbs.AuthenticationRemote;
import com.ejbs.BookingRemote;
import com.entities.Chambre;
import com.entities.Utilisateur;

import com.models.JsonResult;
import com.security.JwtSecurity;
import com.utilities.EmailSender;
import com.utilities.Util;

@Stateless
@Path("/bookings")
public class BookingController {
	@EJB(lookup ="ejb:/HotelBookersEJB//Booking!com.ejbs.BookingRemote")

    private BookingRemote bookingRemote;
	private JwtSecurity secur=new JwtSecurity();
	@EJB(lookup ="ejb:/HotelBookersEJB//Authentication!com.ejbs.AuthenticationRemote")
	public AuthenticationRemote authenticationRemote;

	@Path("/bookRoom")
	@POST
    @Produces("application/json")
	public JsonResult bookRoom(@FormParam("token")String token,@FormParam("dateDeb") String dateDeb,@FormParam("dateFin") String dateFin,@DefaultValue("-1") @FormParam("nbEnfant") int nbEnfant,@DefaultValue("-1") @FormParam("nbAdulte") int nbAdulte,@DefaultValue("-1") @FormParam("idChambre")long idChambre ) {
		//verification des champs
		if(token==null||dateDeb==null || dateFin ==null ||nbEnfant==-1 || nbAdulte==-1|| idChambre==-1
			||token.isEmpty()||dateDeb.isEmpty() || dateFin.isEmpty())
				return new JsonResult(401, "Tous les champs doivent etre précisés");
		
		//verification date debut <= date Fin
		if(!Util.isDateDebGreaterThenDateFin(dateDeb, dateFin))
			return new JsonResult(401, "La date de debut doit etre inferieur a la date de fin");
		
		//valider le token
		String idUser=secur.validateToken(token);
		if (idUser.isEmpty()) return new JsonResult(401, "user not connected!");
		Utilisateur u=authenticationRemote.getUserFromId(Long.parseLong(idUser));
		//Chambre dispo
		if(!bookingRemote.isRoomAvailableForPeriod(idChambre, dateDeb, dateFin)) 
			 return new JsonResult(401, "la chambre n'est pas disponible pour cette periode");
		//verifier le nombre de lit
		Chambre c=bookingRemote.getChambreFromId(idChambre);
		if(c.getnbLits()<nbAdulte+nbEnfant) return new JsonResult(401, "nombre de lits insuffisants");
		//faire la reservation
		bookingRemote.addRowInReservation(dateDeb, dateFin, nbEnfant, nbAdulte, idChambre,idUser);
		//envoi du mail
//		String [] from= {"abdelkarim.drareni@gmail.com"};
		String [] from= {u.getEmail()};

		String [] cc= {"abdelkarim.drareni@gmail.com"};
		String message = "<div style=\"color: #444444; font-family: Roboto Condensed,Helvetica,arial; font-size: 25px; font-weight: 600; line-height: 22px; padding: 0px; text-align: center;\">Cher client Pyramide,</div>\n" + 
				"<p style=\"text-align: left;\"><span style=\"color: #000000;\">Nous sommes ravis que vous ayez choisi de s&eacute;journer dans un des <span id=\"spans0e0\" class=\"sac\">h&ocirc;tels</span> de notre chaine d'<span id=\"spans0e1\" class=\"sac\">h&ocirc;tellerie</span> PYRAMIDE.</span></p>\n" + 
				"<p style=\"text-align: left;\"><br /><span style=\"color: #000000;\">Nous vous confirmons la r&eacute;servation de <span class=\"orange1\">la chambre <em><strong>"+c.getNumero()+"</strong></em> dans l'h<span id=\"spans1e0\" class=\"sac\">&ocirc;tel</span> <em><strong>"+c.getHotel().getNom()+"</strong></em></span> au nom de <strong><em><span class=\"orange1\">"+u.getNom()+" "+u.getPrenom()+"</span></em></strong> pour la p&eacute;riode suivante:<strong><em> <span class=\"orange1\">"+dateDeb+"-"+dateFin+"</span></em></strong><span class=\"orange1\"></span></span><br /><br /><br />"
				+ "<span style=\"color: #000000;\">En vous remerciant par avance, nous vous adressons,&nbsp;<span class=\"orange1\">Madame/Monsieur</span>, nos salutations distingu&eacute;es,</span><br /><br />"
				+ "<span style=\"color: #333333;\"><em>Nous vous souhaitons un agr&eacute;able s&eacute;jour,<br /> "
				+ "Votre &eacute;quipe Pyramide.</em></span></p>";
		
		try {
			EmailSender.sendMail
			("Confirmation Reservation",message,from ,cc );
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new JsonResult(201, "Reservation reussi");
	}
	
}
