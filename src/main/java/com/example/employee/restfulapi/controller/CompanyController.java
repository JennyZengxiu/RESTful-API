package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    private CompanyRepository companyRepository;

    //获取company列表
    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getCompanys(){
        return companyRepository.findAll();
    }

    //获取某个具体company
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Company getCompany(@PathVariable Long id){
        return companyRepository.findById(id);
    }

    //获取某个具体company下所有employee列表
    @RequestMapping(value = "/{id}/employees",method = RequestMethod.GET)
    public List<Employee> getEmployees(@PathVariable Long id){
        return companyRepository.findEmployeesByCompanyId(id);
    }
}
