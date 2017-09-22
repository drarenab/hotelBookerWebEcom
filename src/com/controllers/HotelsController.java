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
import com.models.JsonResult;
import com.utilities.JpaPojoConverter;

@Stateless
@Path("/hotels")
public class HotelsController {

	@EJB(lookup ="ejb:/HotelBookersEJB//Hotels!com.ejbs.HotelsRemote")
    private HotelsRemote hotelsRemote;
	
	@GET
	@Path("/room/search")
	@Produces("application/json")
	public JsonResult getAllRooms()
    {
		long errCode=201;
		Object result;
		JsonResult jsonResult;
		List<Chambre> listChambres = hotelsRemote.getALlRooms();
		if(listChambres.isEmpty()) {
			errCode = 401;
			result = new String("Aucune chambre n'as été trouvé");
			jsonResult = new JsonResult(errCode,result);
			return jsonResult;
		}	
		
		List<ChambreModel> chambreModelList = new ArrayList();
		ChambreModel chambreModel;
		
		for(Chambre chambre : listChambres ) {
			chambreModel = JpaPojoConverter.chambreJpaToPojo(chambre);
			chambreModelList.add(chambreModel);
		}
		
		jsonResult = new JsonResult(errCode,chambreModelList);
		
		return jsonResult;
    }
	
}
