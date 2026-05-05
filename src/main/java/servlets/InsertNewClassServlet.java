package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageClassController;

import java.io.IOException;
import java.time.LocalTime;

import entities.Activity;
import entities.Class;
import entities.People;

public class InsertNewClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageClassController cont = new ManageClassController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		
		String day = request.getParameter("day");
		LocalTime beginHour = LocalTime.parse(request.getParameter("beginHour"));
		LocalTime endHour = LocalTime.parse(request.getParameter("endHour"));
		int idActivity = Integer.parseInt(request.getParameter("idActivity"));
		
		Activity activity = cont.getActivityById(idActivity);
		
		Class c = new Class(0, day, beginHour, endHour, p, activity);
		cont.createClass(c);
		
		response.sendRedirect("ManageTaughtClassesServlet");
	}

}
