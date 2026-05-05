package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageClassController;

import java.io.IOException;
import java.util.ArrayList;

import entities.People;
import entities.Class;

public class ManageClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageClassController cont = new ManageClassController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		ArrayList<Class> classes = cont.getClassesByPeople(p);
		session.setAttribute("classes", classes);
		request.getRequestDispatcher("WEB-INF/jsps/ManageClasses.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
