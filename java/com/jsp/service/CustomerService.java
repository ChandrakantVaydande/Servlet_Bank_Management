package com.jsp.service;

import com.jsp.dao.CustomerDao;
import com.jsp.dto.Customer;

public class CustomerService {
	
	
	CustomerDao customerDao = new CustomerDao();

// =========================toSave Customer Details=====================

	public Customer saveDetails(Customer customer) {

		return customerDao.saveCustomer(customer);
	}

// ===============================toFetch Customer
// Details===============================

	public Customer getCustomerByID(int id) {

		return customerDao.fetchCustomerByID(id);
	}

// ====================================toUpdate_Customer_Data===============================

	public Customer updateData(int id, String email, String password) {

		Customer customer = customerDao.fetchCustomerByID(id);

		customer.setEmail(email);
		customer.setPassword(password);

		return customerDao.updateCustomerDetails(customer, id);

	}

// ====================================toUpdate_Customer_Data===============================

	public Customer updateCustomer( Customer customer) {

		return customerDao.updateCustomer( customer);

	}

// =================================toDelete_Customer_Data==============================

	public boolean deleteCustomer(int id) {

		if (id > 0) {
			return customerDao.deleteCustomerDetails(id);
		} else {
			System.out.println("Wrong ID");
			return false;
		}
	}

// ================================== to Credit Amount ===========================================

	public Customer creditAmount(int customer_id, double amount) {
		
		Customer customer = customerDao.fetchCustomerByID(customer_id);

		if (customer != null) {
			if (customer.getStatus() != null) {

				customer.setBalance(customer.getBalance() + amount);
				System.out.println("Amount " + amount + " credited to your account. Avl Bal Rs:" + customer.getBalance());
				customerDao.updateCustomer(customer);
				} else
				System.out.println("Status is Not Approved...");
			} else
			System.out.println("Customer Id Not Found");
		
		return customer;

	}

// ======================================== To Debit Amount =========================================

	public Customer debitAmount(int customer_id, double amount) {
		Customer customer = customerDao.fetchCustomerByID(customer_id);
		if (customer != null) {
			if (customer.getStatus() != null) {
				if (customer.getBalance() > amount) {
					customer.setBalance(customer.getBalance() - amount);
					System.out.println("Amount " + amount + " debited from your account. Avl Bal Rs:" + customer.getBalance());
					customerDao.updateCustomer(customer);
				
				} else {
					System.out.println("Insufficient balance. Debit transaction failed.");
					return null;
					}
				} else {
				System.out.println("Status is Not Approved...");
				}
			} else {
			System.out.println("Customer Id Not Found");
		}
		return customer;
	}

// =====================================toTransfer_Money=========================

	public Customer transferMoney(int sender, int reciever, double amount) {

		Customer sender_account = customerDao.fetchCustomerByID(sender);
		Customer reciver_account = customerDao.fetchCustomerByID(reciever);

		if (sender_account.getStatus() != null && reciver_account.getStatus() != null) {

			if (sender_account != null && reciver_account != null) {

				if (sender_account.getBalance() >= amount) {
					sender_account.setBalance(sender_account.getBalance() - amount);
					reciver_account.setBalance(reciver_account.getBalance() + amount);
					customerDao.updateCustomer(sender_account);
					customerDao.updateCustomer(reciver_account);

				} else
					System.out.println("Amount Tranfer cannot be more than Balance");

			} else
				System.out.println("Account ID Doesn't Exists");

		} else
			System.out.println("Customer is Not Approved, Get Approved First");
		return reciver_account;
	}


}
