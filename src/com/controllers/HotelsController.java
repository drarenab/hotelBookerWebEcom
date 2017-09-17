package com.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ejbs.BookingRemote;
import com.ejbs.HotelsRemote;
import com.entities.Chambre;

@Stateless
@Path("/hotels")
public class HotelsController {
	@EJB(lookup ="ejb:/HotelBookersEJB//Hotels!com.ejbs.HotelsRemote")
    private HotelsRemote hotelsRemote;
	
	@GET
	@Path("/room/search")
    @Produces("application/json")
    public java.util.List<Chambre> getAllRooms()
    {
		return hotelsRemote.getALlRooms();
    }
	
}
