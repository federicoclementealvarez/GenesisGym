package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.ManageAdminController;
import java.io.IOException;
import entities.ExerciseType;

public class InsertNewExerciseTypeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageAdminController cont = new ManageAdminController();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int idActivity = Integer.parseInt(request.getParameter("idActivity"));
		
		ExerciseType et = new ExerciseType(0, name, description);
		cont.createExerciseType(et, idActivity);
		
		response.sendRedirect("ManageExerciseTypesServlet");
	}
}
