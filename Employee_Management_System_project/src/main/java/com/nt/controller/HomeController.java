package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.entity.Employee;
import com.nt.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	
	@Autowired
	private EmpService empservice;
	
	
	@GetMapping("/")
     public String index(Model m)
	//public String index()
	{
		List<Employee> list=empservice.getAllEmp();
		m.addAttribute("empList",list);
		return "index";
	}
	
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave()
	{
		return "emp_save";
	}
	
	@GetMapping("/EditEmp")
	public String EditEmp(@PathVariable int id, Model m)
	{
		Employee emp = empservice.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit_emp";
	}
	
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp,HttpSession session)
	{
		//System.out.println(emp);
		
		Employee newEmp=empservice.saveEmp(emp);
		
		if (newEmp != null) {
			 //System.out.println("save success");
			session.setAttribute("msg", "Register sucessfully");
		} else {
			 //System.out.println("something wrong on server");

			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/loadEmpSave";
	
		//return "emp_save";	
    }
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee updateEmp = empservice.saveEmp(emp);

		if (updateEmp != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {
		boolean f = empservice.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}


}

