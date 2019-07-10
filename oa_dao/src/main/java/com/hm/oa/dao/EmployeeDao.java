package com.hm.oa.dao;

import com.hm.oa.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/7/007 2:54
 */
@Repository("employeeDao")
public interface EmployeeDao {

    void insert(Employee department);

    void update(Employee department);

    void delete(String sn);

    Employee select(String sn);

    List<Employee> selectAll();

}
