package com.jsp.managerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Manager;
import com.jsp.service.ManagerService;

@WebServlet("/managerLogin")
public class ManagerLogin extends HttpServlet{
	
	ManagerService managerServices=new ManagerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ID=req.getParameter("managerId");
		int id=Integer.parseInt(ID);
		
		String Password=req.getParameter("managerpassword");
		
		Manager m=managerServices.fetchManagerDetails(id);
		 
		if(m!=null) {

			RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerRegister.jsp");
			
			requestDispatcher.forward(req, resp);
		}else {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"ID not Found"+"</h1></body></html>");
		}
	}

}
