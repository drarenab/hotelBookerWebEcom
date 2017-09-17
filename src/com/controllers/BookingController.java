package com.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ejbs.BookingRemote;
import com.entities.Chambre;

@Stateless
@Path("/bookings")
public class BookingController {
	@EJB(lookup ="ejb:/HotelBookersEJB//Booking!com.ejbs.BookingRemote")
    private BookingRemote bookingRemote;
	
	@GET
    @Produces("application/json")
    public java.util.List<Chambre> getAllRooms()
    {
		return bookingRemote.getALlRooms();
    }
	
}
