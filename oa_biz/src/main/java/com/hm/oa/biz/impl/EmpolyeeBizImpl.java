package com.hm.oa.biz.impl;

import com.hm.oa.biz.EmpolyeeBiz;
import com.hm.oa.dao.EmployeeDao;
import com.hm.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/7/007 3:18
 */
@Service("empolyeeBiz")
public class EmpolyeeBizImpl implements EmpolyeeBiz {

    @Autowired
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
