package com.ikubinfo.primefaces.service.admin;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.admin.Admin;
import com.ikubinfo.primefaces.repository.admin.AdminRepository;

@Service
public class AdminService {

	private AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	public int getAdminId(String email) {
		return adminRepository.getAdminId(email);
	}

	public Admin getAdmin(int id) {
		return adminRepository.getAdmin(id);
	}

	public boolean save(Admin editAdmin) {
		return adminRepository.save(editAdmin);

	}
}
