package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    private EmployeeRepository employeeRepository;

    //获取employee列表
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
}
