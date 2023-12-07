package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageExerciseController;

import java.io.IOException;

import entities.Exercise;
import entities.Routine;


public class DeleteExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageExerciseController cont = new ManageExerciseController();
		HttpSession session = request.getSession();
		
		int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
		
		cont.deleteExerciseById(exerciseId);
		
		Routine r = (Routine) session.getAttribute("routine");
		
		int i =0;
		for(Exercise e: r.getExercises()) {
			if(e.getId()==exerciseId) {
				break;
			}
			i++;
		}
		
		r.getExercises().remove(i);
		
		session.setAttribute("routine", r);
		
		request.getRequestDispatcher("WEB-INF/jsps/ShowRoutine.jsp").forward(request, response);
	}

}
