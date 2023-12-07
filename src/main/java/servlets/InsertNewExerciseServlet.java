package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageExerciseController;

import java.io.IOException;
import java.util.ArrayList;

import entities.Exercise;
import entities.ExerciseType;
import entities.Routine;


public class InsertNewExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageExerciseController cont = new ManageExerciseController();
		HttpSession session = request.getSession();
		Routine r = (Routine) session.getAttribute("routine");
		ExerciseType exType = new ExerciseType(Integer.parseInt(request.getParameter("exerciseTypeId")));
		
		Exercise e = new Exercise(
				exType,
				Integer.parseInt(request.getParameter("repetitionQuantity")),
				Integer.parseInt(request.getParameter("setQuantity")),
				r
		);
		
		e.setRestTimeBetweenSetsInSeconds(Integer.parseInt(request.getParameter("restTimeBetweenSetsInSeconds")));
		
		cont.insertExercise(e);
		
		ArrayList<Routine> routines = (ArrayList<Routine>) session.getAttribute("routines");
		
		for(Routine routine:routines) {
			if(routine.getId()==r.getId()) {
				routine.setExerciseQuantity(routine.getExerciseQuantity()+1);
				break;
			}
		}
		
		session.setAttribute("routines", routines);
		
		request.getRequestDispatcher("WEB-INF/jsps/ManageRoutines.jsp").forward(request, response);
	}

}
