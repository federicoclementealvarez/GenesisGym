package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.ManageAdminController;
import java.io.IOException;
import entities.Plan;

public class InsertNewPlanServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		String name = request.getParameter("name");
		double rate = Double.parseDouble(request.getParameter("rate"));
		String[] activityIds = request.getParameterValues("idActivity");
		
		Plan p = new Plan(0, name, rate);
		cont.createPlan(p, activityIds);
		
		response.sendRedirect("ManageAdminPlansServlet");
	}
}
