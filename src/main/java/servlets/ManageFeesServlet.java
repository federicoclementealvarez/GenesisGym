package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageFeeController;

import java.io.IOException;
import java.util.ArrayList;

import entities.Fee;
import entities.People;

public class ManageFeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageFeeController cont = new ManageFeeController();
		HttpSession session = request.getSession();
		People p = (People) session.getAttribute("user");
		
		// Verificar y crear cuota del mes actual
		cont.checkAndCreateCurrentMonthFee(p);
		
		// Obtener cuotas impagas
		ArrayList<Fee> unpaidFees = cont.getUnpaidFees(p);
		session.setAttribute("unpaidFees", unpaidFees);
		
		request.getRequestDispatcher("WEB-INF/jsps/ManageFees.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
