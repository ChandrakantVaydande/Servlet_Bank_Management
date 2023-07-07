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

@WebServlet("/approveCustomer")
public class ApproveCustomer extends HttpServlet {
	
	ManagerService managerServices=new ManagerService();
	CustomerService customerServices=new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String customer_ID=req.getParameter("customerId");
		int id=Integer.parseInt(customer_ID);
		
		String manager_ID=req.getParameter("managerId");
		int id2=Integer.parseInt(manager_ID);
		
		Manager manager=managerServices.fetchManagerDetails(id2);
		Customer customer=customerServices.getCustomerByID(id);
		
		if(customer.getStatus().equals("Block")) 
		{
			
			if(manager.getId()==customer.getManager().getId())
			{
				customer.setStatus("Approve");
				customerServices.updateCustomer(customer);
				
				
			}else {
				PrintWriter printWriter=resp.getWriter();
				printWriter.write("<html><body><h1>"+manager.getName()+" you are not Authorized To Approve "+customer.getId()+" customer"+"</h1></body></html>");
		
			}
		}
		 else {
			 PrintWriter printWriter=resp.getWriter();
				printWriter.write("<html><body><h1>"+" Customer is still not approve "+"</h1></body></html>");
		
		 }
		
		Customer c_update=customerServices.updateCustomer(customer);
		if(customer.getStatus().equals("Approve")&&c_update!=null) {
			PrintWriter printWriter=resp.getWriter();
			printWriter.write("<html><body><h1>"+" Customer is Approved! "+"</h1></body></html>");
		}
		
	}

}
