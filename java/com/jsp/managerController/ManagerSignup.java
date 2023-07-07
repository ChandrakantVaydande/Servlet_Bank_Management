package com.jsp.managerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Manager;
import com.jsp.service.ManagerService;

@WebServlet("/managerSignUp")
public class ManagerSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Manager manager=new Manager();
		ManagerService managerServices=new ManagerService();
		String name=req.getParameter("managername");
		String email=req.getParameter("manageremail");
		String password=req.getParameter("managerpassword");
		
		manager.setName(name);
		manager.setEmail(email);
	    manager.setPassword(password);
	    
	    Manager m=managerServices.saveManagerDetails(manager);
	    
	    if(m!=null) {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"Welcome"+"</h1></body></html>");
	    }
	}

}
