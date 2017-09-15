package com.logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Servlet implementation class Doadd
 */

@WebServlet("/Doadd")
public class Doadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(lookup ="ejb:/HotelBookersEJB//Rooms!com.logic.RoomsRemote")
    private RoomsRemote abc;
    
	
	@EJB(lookup ="ejb:/HotelBookersEJB//Db!com.logic.DbRemote")
    private DbRemote az;

    /*
     * @see HttpServlet#HttpServlet()
     */
    public Doadd() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String as=request.getParameter("a");
		int a=Integer.parseInt(as);
		as=request.getParameter("b");
		int b=Integer.parseInt(as);
		
		int result=0;
		
		try {
			result=abc.add(a, b);
		}catch(Exception e){e.printStackTrace();}

		PrintWriter out = response.getWriter();
		out.println("<h3>Results for the addition</h3>");
		out.println("Result:"+result);		
		
		response.setContentType("text/html");
		*/
		
		PrintWriter out = response.getWriter();
		out.println("Web :  "+az.getAllRooms().toString());
	      // return "hello world";
	//	out.println(abc.getAllRooms().toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
