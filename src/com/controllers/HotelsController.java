package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ejbs.BookingRemote;
import com.ejbs.HotelsRemote;
import com.entities.Chambre;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.ChambreModel;
import com.models.HotelModel;
import com.utilities.JpaPojoConverter;

@Stateless
@Path("/hotels")
public class HotelsController {
	@EJB(lookup ="ejb:/HotelBookersEJB//Hotels!com.ejbs.HotelsRemote")
    private HotelsRemote hotelsRemote;
	
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
	
		return chambreModelList;
    }
	
}
