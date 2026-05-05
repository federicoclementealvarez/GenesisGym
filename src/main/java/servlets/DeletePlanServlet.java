package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.ManageAdminController;
import java.io.IOException;

public class DeletePlanServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		int id = Integer.parseInt(request.getParameter("id"));
		
		boolean deleted = cont.deletePlan(id);
		
		if (deleted) {
			response.sendRedirect("ManageAdminPlansServlet");
		} else {
			response.sendRedirect("ManageAdminPlansServlet?error=used");
		}
	}
}
