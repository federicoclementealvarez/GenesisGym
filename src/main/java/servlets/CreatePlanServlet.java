package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageAdminController;
import java.io.IOException;
import java.util.ArrayList;
import entities.Activity;

public class CreatePlanServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		ArrayList<Activity> activities = cont.getAllActivities();
		HttpSession session = request.getSession();
		session.setAttribute("allActivities", activities);
		request.getRequestDispatcher("WEB-INF/jsps/CreatePlan.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
