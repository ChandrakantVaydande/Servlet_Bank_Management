package com.jsp.service;

import java.util.List;

import com.jsp.dao.AdminDao;
import com.jsp.dto.Admin;
import com.jsp.dto.Manager;

public class AdminService {
	
	AdminDao adminDao=new AdminDao();
	//=======================toSave_Admin_Details=====================
	
		public Admin saveAdminDetails(Admin admin) {
			
			return adminDao.saveAdmin(admin) ;
		}
		
	//========================toFetch_All_Manager======================
		
		public List<Manager> getAllManager(){
			
			return adminDao.getAllManagerDetails();
		}
}
