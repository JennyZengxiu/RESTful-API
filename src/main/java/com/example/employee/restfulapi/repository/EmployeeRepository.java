package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    //增加一个employee
    Employee save(Employee employee);

    //更新某个employee
    @Modifying
    @Transactional
    @Query("update Employee e set e.name = ?2, e.age = ?3, e.gender = ?4, e.salary = ?5, e.companyId = ?6 where e.id = ?1")
    int updateById(Long id, String name, Integer age, String gender, Integer salary, Long companyId);

    //删除某个employee
    @Transactional
    void deleteById(Long id);
}
