package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;

import java.io.IOException;

import entities.People;
import entities.Plan;


public class ManagePlansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller cont = new Controller();
		HttpSession session = request.getSession();
		
		People p = (People) session.getAttribute("user");
		Plan plan = p.getPlan();
		
		plan.setActivities(cont.getActivitiesByPlan(plan));
		
		session.setAttribute("plan", plan);
		
		request.getRequestDispatcher("WEB-INF/jsps/ManagePlans.jsp").forward(request, response);
	}

}
