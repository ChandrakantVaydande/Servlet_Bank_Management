package com.jsp.managerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.dto.Manager;
import com.jsp.service.CustomerService;
import com.jsp.service.ManagerService;

@WebServlet("/blockCustomer")
public class BlockCustomer extends HttpServlet {
	
	CustomerService customerServices=new CustomerService();
	ManagerService managerServices=new ManagerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String customer_ID=req.getParameter("customerId");
		int id=Integer.parseInt(customer_ID);
		
		String manager_ID=req.getParameter("manager_ID");
		int id2=Integer.parseInt(manager_ID);
		
		Manager manager=managerServices.fetchManagerDetails(id2);
		Customer customer=customerServices.getCustomerByID(id);
		
		if(customer.getStatus().equals("Approve")) 
		{
			
			if(manager.getId()==customer.getManager().getId())
			{
				customer.setStatus("Block");
				System.out.println(customer.getStatus());
				customerServices.updateCustomer(customer);
				
				
			}else {
				PrintWriter printWriter=resp.getWriter();
				printWriter.write("<html><body><h1>"+manager.getName()+" you are not Authorized To Block ID:"+customer.getId()+" customer"+"</h1></body></html>");
		
			}
		}
		 else {
			 PrintWriter printWriter=resp.getWriter();
				printWriter.write("<html><body><h1>"+" Customer is still not approve "+"</h1></body></html>");
		
		 }
		

		if(customer.getStatus().equals("Block")) {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+" Customer is Block! "+"</h1></body></html>");
		}	
		
	}
}

