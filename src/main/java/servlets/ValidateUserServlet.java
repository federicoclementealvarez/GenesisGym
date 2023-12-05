package servlets;
import entities.*;
import logic.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class ValidateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public ValidateUserServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		People p = new People(Integer.parseInt( request.getParameter("dni") ) );
		Controller cont = new Controller();
		p.setPassword(request.getParameter("password"));
		p=cont.getByDniAndPass(p);
		if (p != null) {
			p.setPlan(cont.getPlanById(p.getPlan().getId()));
			HttpSession session = request.getSession();
			session.setAttribute("user", p);
			request.setAttribute("pFound", "true");
			request.getRequestDispatcher("WEB-INF/jsps/HomePage.jsp").forward(request, response);
		}
		else {
			request.setAttribute("pFound", "false");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
