package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageFeeController;

import java.io.IOException;
import java.time.LocalDate;
import entities.People;

public class PayFeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageFeeController cont = new ManageFeeController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		
		LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
		cont.payFee(p, dueDate);
		
		response.sendRedirect("ManageFeesServlet");
	}

}
