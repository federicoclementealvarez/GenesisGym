package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.ManageAdminController;
import java.io.IOException;

public class DeleteExerciseTypeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		int id = Integer.parseInt(request.getParameter("id"));
		
		boolean deleted = cont.deleteExerciseType(id);
		
		if (deleted) {
			response.sendRedirect("ManageExerciseTypesServlet");
		} else {
			response.sendRedirect("ManageExerciseTypesServlet?error=used");
		}
	}
}
