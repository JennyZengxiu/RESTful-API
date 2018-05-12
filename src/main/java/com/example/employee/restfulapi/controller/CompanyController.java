package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    //分页查询，page等于1，pageSize等于5
   @RequestMapping(value = "/page/{page}/pageSize/{pageSize}",method = RequestMethod.GET)
    public Page<Company> getCompanys(@PathVariable int page,@PathVariable int pageSize){
       return companyRepository.findAll(new PageRequest(page, pageSize));
   }

   //增加一个company
    @RequestMapping(method = RequestMethod.POST)
    public Company addCompany(Company company) throws Exception{
        if (company.getCompanyName() == null||company.getEmployeesNumber() == null){
            throw new Exception("Invalid input!");
        }
       return companyRepository.save(company);
    }

    //更新某个company
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Company updateById(@PathVariable Long id, @ModelAttribute Company company){
        companyRepository.updateById(id,company.getCompanyName(),company.getEmployeesNumber());
        return companyRepository.findById(id);
    }

    //删除某个company以及名下所有employees
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Company deleteCompany(@PathVariable Long id){
        companyRepository.deleteById(id);
        return null;
    }
}
