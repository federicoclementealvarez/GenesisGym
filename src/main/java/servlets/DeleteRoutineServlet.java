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


public class DeleteRoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageRoutineController cont = new ManageRoutineController();
		HttpSession session = request.getSession();
		Routine r = (Routine) session.getAttribute("routine");
		cont.deleteRoutine(r);
		
		ArrayList<Routine> routines = (ArrayList<Routine>) session.getAttribute("routines");
		
		int i =0;
		for(Routine routine: routines) {
			if(routine.getId()==r.getId()) {
				break;
			}
			i++;
		}
		
		routines.remove(i);
		
		session.setAttribute("routines", routines);
		
		request.getRequestDispatcher("WEB-INF/jsps/ManageRoutines.jsp").forward(request, response);
	}

}
