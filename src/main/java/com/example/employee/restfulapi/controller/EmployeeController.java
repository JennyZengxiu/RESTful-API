package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

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

    //筛选出所有男性Employee
    @RequestMapping(value = "/male",method = RequestMethod.GET)
    public List<Employee> getMaleEmployees(){
        return employeeRepository.findByGender("male");
    }

    //增加一个employee
    @RequestMapping(method = RequestMethod.POST)
    public Employee addEmployee(Employee employee)throws Exception{
        if (employee.getName() == null
                || employee.getAge() == null
                || employee.getGender() == null
                || employee.getSalary() == null
                || employee.getCompanyId() == null) {
            throw new Exception("Invalid input!");
        }
        return employeeRepository.save(employee);
    }

    //更新某个employee
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Employee updateById(@PathVariable Long id, @ModelAttribute Employee employee){
        employeeRepository.updateById(id,employee.getName(),employee.getAge(),employee.getGender(),employee.getSalary(),employee.getCompanyId());
        return employeeRepository.findById(id);
    }

    //删除某个employee
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Employee deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return null;
    }
}
