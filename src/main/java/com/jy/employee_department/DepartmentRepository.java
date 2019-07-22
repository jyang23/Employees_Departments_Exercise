package com.jy.employee_department;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DepartmentRepository extends CrudRepository<Department,Long>
{
    ArrayList<Department> findByName(String name);
    ArrayList<Department> findByField(String field);
    ArrayList<Department> findByJobtitle(String jobtitle);
}
