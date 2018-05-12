package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    //获取company列表
    List<Company> findAll();

    //获取某个具体company
    Company findById(Long id);

    //获取某个具体company下所有employee列表
    @Query("select e from Employee e where e.companyId = ?1")
    List<Employee> findEmployeesByCompanyId(Long id);
}
