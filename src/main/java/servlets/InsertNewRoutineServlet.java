package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageRoutineController;

import java.io.IOException;

import entities.People;
import entities.Routine;

public class InsertNewRoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageRoutineController cont = new ManageRoutineController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		Routine r = new Routine(request.getParameter("routineName"),"Personal",p);
		cont.insertRoutine(r);
		request.getRequestDispatcher("WEB-INF/jsps/HomePage.jsp").forward(request, response);
	}

}
