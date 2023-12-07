package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageExerciseController;

import java.io.IOException;
import java.util.ArrayList;

import entities.Plan;
import entities.People;
import entities.ExerciseType;

public class CreateExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageExerciseController cont = new ManageExerciseController();
		HttpSession session = request.getSession();
		
		People p = (People) session.getAttribute("user");
		Plan plan = p.getPlan();
		
		ArrayList<ExerciseType> exTypes = cont.getExerciseTypesByPlan(plan);
		
		session.setAttribute("exerciseTypes", exTypes);
		
		request.getRequestDispatcher("WEB-INF/jsps/CreateExercise.jsp").forward(request, response);
	}

}
