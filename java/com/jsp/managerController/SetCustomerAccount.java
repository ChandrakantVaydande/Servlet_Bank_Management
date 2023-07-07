package com.jsp.managerController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.CustomerDao;
import com.jsp.dao.ManagerDao;
import com.jsp.dto.Customer;
import com.jsp.dto.Manager;
import com.jsp.service.CustomerService;
import com.jsp.service.ManagerService;

@WebServlet("/setAccountDetails")
public class SetCustomerAccount extends HttpServlet{
	
	Random random=new Random();
	ManagerService managerServices=new ManagerService();
	ManagerDao managerDao=new ManagerDao();
	CustomerDao customerDao=new CustomerDao();
	CustomerService customerServices=new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String customerId = req.getParameter("customerId");
		int id=Integer.parseInt(customerId);
		String managerId = req.getParameter("managerID");
		int id1=Integer.parseInt(managerId);
        String accountType = req.getParameter("accountType");
        String accountStatus = req.getParameter("accountStatus");
        int acc_no=1000000000 + random.nextInt(900000000);
		String ifsc ="SCWT5530";
        
        Manager manager=managerServices.fetchManagerDetails(id1);
        Customer customer=customerServices.getCustomerByID(id);
        
       
        customer.setAcc_no(acc_no);
        customer.setAcc_type(accountType);
        customer.setIfsc(ifsc);
        customer.setStatus(accountStatus);
        
        
        customer.setManager(manager);
		manager.getCustomers().add(customer);
		
		
		 Manager save=managerDao.updateManager(manager);
		 Customer save1=customerDao.updateCustomer(customer);
       

        PrintWriter printWriter=resp.getWriter();
		printWriter.write("<html><body><h1>"+"Customer Account Set!"+"</h1></body></html>");	
	
	}
}
