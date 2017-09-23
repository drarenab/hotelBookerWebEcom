package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ejbs.BookingRemote;
import com.ejbs.HotelsRemote;
import com.entities.Chambre;
import com.models.ChambreModel;
import com.utilities.JpaPojoConverter;

@Stateless
@Path("/hotels")
public class HotelsController {

	@EJB(lookup ="ejb:/HotelBookersEJB//Hotels!com.ejbs.HotelsRemote")
    private HotelsRemote hotelsRemote;
	@EJB(lookup ="ejb:/HotelBookersEJB//Booking!com.ejbs.BookingRemote")
	public BookingRemote bookingRemote;
	@GET
	@Path("/room/search")
	@Produces("application/json")
	public List<ChambreModel> getAllRooms()
    {
		List<Chambre> listChambres = hotelsRemote.getALlRooms();
		
		List<ChambreModel> chambreModelList = new ArrayList();
		ChambreModel chambreModel;
		
		for(Chambre chambre : listChambres ) {
			chambreModel = JpaPojoConverter.chambreJpaToPojo(chambre);
			chambreModelList.add(chambreModel);
		}
//		JsonResult jsonResult = new JsonResult();
//		jsonResult.setCode(code);
		return chambreModelList;
    }
		
	@GET
	@Path("/room/search/{ville}/{dateDeb}/{dateFin}/{nbAdulte}/{nbEnfant}")
	@Produces("application/json")
	public List<ChambreModel> getRoomsWithFilter(@PathParam("ville")String ville,
			@PathParam("dateDeb") String dateDeb,
			@PathParam("dateFin") String dateFin,
			@PathParam("nbEnfant") int nbEnfant,
			@PathParam("nbAdulte") int nbAdulte)
    {
		List<Chambre> listChambres = hotelsRemote.getRoomsForFilter(ville, dateDeb, dateFin, nbAdulte, nbEnfant);
		
		List<ChambreModel> chambreModelList = new ArrayList();
		ChambreModel chambreModel;
		
		for(Chambre chambre : listChambres ) {
			if(bookingRemote.isRoomAvailableForPeriod(chambre.getId(),dateDeb,dateFin)==true) {
				chambreModel = JpaPojoConverter.chambreJpaToPojo(chambre);
				chambreModelList.add(chambreModel);
			}
			
		}
//		JsonResult jsonResult = new JsonResult();
//		jsonResult.setCode(code);
		return chambreModelList;
    }
	
	
}
