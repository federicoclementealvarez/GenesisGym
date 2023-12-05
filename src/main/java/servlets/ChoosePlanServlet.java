package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;

import java.io.IOException;

import entities.People;

public class ChoosePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller cont = new Controller();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		p.setPlan(cont.getPlanById(Integer.parseInt(request.getParameter("planId"))));
		session.setAttribute("user", p);
		cont.insertPeople(p);
		request.getRequestDispatcher("WEB-INF/jsps/HomePage.jsp").forward(request, response);
	}

}
