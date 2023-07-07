package com.jsp.managerController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager")
public class Controller extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String register=req.getParameter("register");
		String login=req.getParameter("login");
		String update=req.getParameter("update");
		
          if(register!=null) {
			
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerSignUp.jsp");
			
			requestDispatcher.forward(req, resp);
		}else
		if(update!=null) {
					
	    }else
		if(login!=null) {

			RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerLogin.jsp");
			
			requestDispatcher.forward(req, resp);
						
		}
	}
}

