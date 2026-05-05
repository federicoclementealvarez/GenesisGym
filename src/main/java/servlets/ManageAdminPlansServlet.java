package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageAdminController;
import java.io.IOException;
import java.util.ArrayList;
import entities.Plan;

public class ManageAdminPlansServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		ArrayList<Plan> plans = cont.getAllPlans();
		HttpSession session = request.getSession();
		session.setAttribute("allAdminPlans", plans);
		request.getRequestDispatcher("WEB-INF/jsps/ManageAdminPlans.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
