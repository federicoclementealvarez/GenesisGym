package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageClassController;

import java.io.IOException;
import entities.People;

public class DeleteEnrollmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageClassController cont = new ManageClassController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		int idClass = Integer.parseInt(request.getParameter("classId"));
		cont.deleteEnrollment(p, idClass);
		response.sendRedirect("ManageClassesServlet");
	}

}
