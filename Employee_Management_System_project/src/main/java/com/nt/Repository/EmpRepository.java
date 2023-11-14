package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Employee;

public interface EmpRepository  extends JpaRepository<Employee, Integer>{

}
