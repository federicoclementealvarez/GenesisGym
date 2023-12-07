package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageRoutineController;

import java.io.IOException;
import java.util.ArrayList;

import entities.Routine;

public class ShowRoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageRoutineController cont = new ManageRoutineController();
		HttpSession session = request.getSession();
		
		int routineId = Integer.parseInt(request.getParameter("routineId"));
		ArrayList<Routine> routines = (ArrayList<Routine>) session.getAttribute("routines");
		
		Routine r = null;
		
		for(Routine routine : routines) {
			if(routine.getId()==routineId) {
				r = routine;
				break;
			}
		}
		
		r.setExercises(cont.getExercisesByRoutine(r));
		
		session.setAttribute("routine", r);
		
		request.getRequestDispatcher("WEB-INF/jsps/ShowRoutine.jsp").forward(request, response);
	}

}
