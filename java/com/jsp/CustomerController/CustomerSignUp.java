package com.jsp.CustomerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

@WebServlet("/customerSignUp")
public class CustomerSignUp extends HttpServlet{
	
	Customer customer=new Customer();
	CustomerService customerServices=new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("username");
		String email=req.getParameter("useremail");
		String pan=req.getParameter("userpan");
		String password=req.getParameter("userpassword");
		String Amount=req.getParameter("useramount");
		double balance=Double.parseDouble(Amount);
		
		
		customer.setName(name);
		customer.setEmail(email);
		customer.setPan(pan);
		customer.setPassword(password);
		customer.setBalance(balance);
		
		Customer c=customerServices.saveDetails(customer);
		
		if(c!=null) {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"Welcome"+"</h1></body></html>");
			
		}
	}

}
