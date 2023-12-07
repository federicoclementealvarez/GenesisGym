package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.SignUpLoginController;

import java.io.IOException;
import java.time.LocalDate;

import entities.People;

public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		SignUpLoginController cont = new SignUpLoginController();
		People p = this.assignAttibutes(request, cont);
		this.validateUser(p, cont, request, response);
	}
	
	private void validateUser(People p, SignUpLoginController cont, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("teacherFound", null);
		request.setAttribute("clientFound", null);
		request.setAttribute("clientHasNoTeacher", null);
		boolean clientFound = cont.peopleExists(p);
		if (clientFound) {
			request.setAttribute("clientFound", "true");
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		
		if (p.getType().equals("client") && request.getParameter("teacherDni")=="") {
			request.setAttribute("clientHasNoTeacher", "true");
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		
		if (p.getTeacher()==null && request.getParameter("teacherDni")!="") {
			request.setAttribute("teacherFound", "false");
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		
		if(p.getType().equals("client")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", p);
			request.setAttribute("plans", cont.getAllPlans());
			request.getRequestDispatcher("WEB-INF/jsps/ChoosePlan.jsp").forward(request, response);
			return;
		}
		else {
			p.setPlan(cont.getPlanByName("Profesor"));
			HttpSession session = request.getSession();
			session.setAttribute("user", p);
			cont.insertPeople(p);
			request.getRequestDispatcher("WEB-INF/jsps/HomePage.jsp").forward(request, response);
			return;
		}
	}
	
	private People assignAttibutes(HttpServletRequest request, SignUpLoginController cont) throws ServletException{
		People teacher = null;
		if (request.getParameter("teacherDni")!="") {
			teacher = cont.getTeacherBasicInfo(Integer.parseInt(request.getParameter("teacherDni")));
		}
		
		People p = new People(
				Integer.parseInt(request.getParameter("dni")),
				request.getParameter("name"),
				request.getParameter("lastname"),
				LocalDate.parse(request.getParameter("birthdate")),
				request.getParameter("password"),
				request.getParameter("phoneNumber"),
				request.getParameter("type"));
		
		if(teacher!=null) {
			p.setTeacher(teacher);
		}
		
		return p;
	}

}
