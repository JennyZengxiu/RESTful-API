package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //获取employee列表
    @Override
    List<Employee> findAll();

    //获取某个具体employee
    Employee findById(Long id);

    //分页查询，page等于1，pageSize等于5
    Page<Employee> findAll(Pageable pageable);

    //筛选出所有男性Employee
    List<Employee> findByGender(String gender);
}
