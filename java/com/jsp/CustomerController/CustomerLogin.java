package com.jsp.CustomerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

@WebServlet("/customerId")
public class CustomerLogin extends HttpServlet {
	
	CustomerService customerServices=new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String ID=req.getParameter("customerId");
		int id=Integer.parseInt(ID);
		
		String Password=req.getParameter("customerpassword");
		
		Customer c=customerServices.getCustomerByID(id);
		 
		if(c!=null&&!(c.getStatus().equals("Block"))) {

			RequestDispatcher requestDispatcher=req.getRequestDispatcher("Customer_Page.jsp");
			
			requestDispatcher.forward(req, resp);
		}else
		if(c.getStatus().equals("Block")){
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"ID Blocked...! Contact Your Branch."+"</h1></body></html>");
		}
	}

}
