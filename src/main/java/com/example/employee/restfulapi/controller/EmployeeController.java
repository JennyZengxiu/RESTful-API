package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
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

    //获取某个具体employee
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id){
        return employeeRepository.findById(id);
    }

    //分页查询，page等于1，pageSize等于5
    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}",method = RequestMethod.GET)
    public Page<Employee> getEmployees(@PathVariable int page, @PathVariable int pageSize){
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }
}
