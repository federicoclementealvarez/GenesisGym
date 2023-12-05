package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;

import java.io.IOException;
import java.util.ArrayList;

import entities.People;
import entities.Routine;

public class ManageRoutinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller cont = new Controller();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		ArrayList<Routine> routines = cont.getRoutinesByPeople(p);
		session.setAttribute("routines",routines);
		request.getRequestDispatcher("WEB-INF/jsps/ManageRoutines.jsp").forward(request, response);
	}

}
