package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.ManageClassController;

import java.io.IOException;

public class DeleteClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageClassController cont = new ManageClassController();
		int idClass = Integer.parseInt(request.getParameter("classId"));
		
		boolean deleted = cont.deleteClass(idClass);
		
		if (deleted) {
			response.sendRedirect("ManageTaughtClassesServlet");
		} else {
			response.sendRedirect("ManageTaughtClassesServlet?error=has_attendants");
		}
	}

}
