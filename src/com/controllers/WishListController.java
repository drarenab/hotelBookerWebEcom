package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ejbs.BookingRemote;
import com.ejbs.WishListRemote;
import com.entities.Chambre;
import com.entities.Reservation;
import com.models.ChambreModel;
import com.models.JsonResult;
import com.models.ReservationModel;
import com.security.JwtSecurity;
import com.utilities.JpaPojoConverter;

@Stateless
@Path("/wish")
public class WishListController {
	@EJB(lookup ="ejb:/HotelBookersEJB//WishList!com.ejbs.WishListRemote")
	public WishListRemote wishListRemote;
	private JwtSecurity secur=new JwtSecurity();
	@GET
	@Path("/allWish")
	@Produces("application/json")
	public JsonResult getWishList() //pas encore fini 
    {
		JsonResult jsonResult;
		List<Reservation> list=wishListRemote.getWishList();
		
		if(list.isEmpty()) {
			jsonResult=new JsonResult(401, "aucune reservation disponible");
			return jsonResult;
		}
		
		ReservationModel reservationModel;
		List<ReservationModel> reservationModellList = new ArrayList();

		for(Reservation reservation : list ) {
			reservationModel = JpaPojoConverter.reservationJpaToPojo(reservation,null);//pour l'instant pas de token
			reservationModellList.add(reservationModel);
		
		}
			jsonResult=new JsonResult(201,reservationModellList );
		
		return jsonResult;
		
    }
	@POST
	@Path("/wishOfClient")
	@Produces("application/json")
	public JsonResult getWishListOfClient(@FormParam("token") String token)
    {		JsonResult jsonResult;
    	if(token==null || token.isEmpty()) {
    		jsonResult=new JsonResult(401, "paramettre manquant");
			return jsonResult;
    	}
    
		String idUser=secur.validateToken(token);
		if (idUser.isEmpty()) {
			jsonResult=new JsonResult(401, "user not connected!");
			return jsonResult;
		}
		
		
		List<Reservation> list=wishListRemote.getWishListForClient(Long.parseLong(idUser));
		if(list.isEmpty()) {
			jsonResult=new JsonResult(401, "vous n'avez aucune reservation de faite");
			return jsonResult;
		}
		ReservationModel reservationModel;
		List<ReservationModel> reservationModellList = new ArrayList();

		for(Reservation reservation : list ) {
			reservationModel = JpaPojoConverter.reservationJpaToPojo(reservation,null);//pour l'instant pas de token
			reservationModellList.add(reservationModel);
		
		}
			jsonResult=new JsonResult(201,reservationModellList );
		
		return jsonResult;
		
		
    }
}
