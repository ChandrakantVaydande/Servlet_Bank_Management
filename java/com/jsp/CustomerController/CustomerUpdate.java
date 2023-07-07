package com.jsp.CustomerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

public class CustomerUpdate extends HttpServlet {
	
	CustomerService customerServices=new CustomerService();
	Customer customer=new Customer();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String CustomerID=req.getParameter("customer_Id");
		int id=Integer.parseInt(CustomerID);
		String email=req.getParameter("customeremail");
		String password=req.getParameter("customerpassword");
		
		Customer c=customerServices.getCustomerByID(id);
		
		if(c!=null) {
			customer.setEmail(email);
			customer.setPassword(password);
		}
		
		Customer update_customer=customerServices.updateData(id, email, password);
		
		if(update_customer!=null) {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"Upadted Successfully!!"+"</h1></body></html>");
		}
		else {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+"Somthing went wrong..."+"</h1></body></html>");
		}
	}

}
