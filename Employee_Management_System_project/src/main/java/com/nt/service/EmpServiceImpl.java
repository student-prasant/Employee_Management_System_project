package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nt.Repository.EmpRepository;
import com.nt.entity.Employee;

import jakarta.servlet.http.HttpSession;


@Service
public class EmpServiceImpl implements EmpService {
	
	
	
	@Autowired
	private EmpRepository  emprepo;

	@Override
	public Employee saveEmp(Employee emp) {
		
		Employee newEmp = emprepo.save(emp);
		return newEmp;
	
	}
	
	

	@Override
	public List<Employee> getAllEmp() {
		
		return emprepo.findAll();
	}
	
	

	@Override
	public Employee getEmpById(int id) {
		
		
		return emprepo.findById(id).get();
	}
	
	
	
	@Override
	public boolean deleteEmp(int id) {
		Employee emp = emprepo.findById(id).get();
		if (emp != null) {
			emprepo.delete(emp);
			return true;
		}
		
		return false;
	}
	
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

}
