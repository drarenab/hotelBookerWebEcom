package com.controllers;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.entities.Room;
import com.logic.DbRemote;


@Stateless
@Path("/hello")
public class MainController {
		//@EJB(lookup ="ejb:/HotelBookersEJB//Rooms!com.logic.DbRemote")
		@EJB(lookup ="ejb:/HotelBookersEJB//Db!com.logic.DbRemote")
	    private DbRemote abc;

	    @GET
	    @Produces("application/json")
	    public java.util.List<Room> defaultl()
	    {
	        return abc.getAllRooms();
	    }
}
