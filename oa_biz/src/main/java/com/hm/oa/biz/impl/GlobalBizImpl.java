package com.hm.oa.biz.impl;

import com.hm.oa.biz.GlobalBiz;
import com.hm.oa.dao.EmployeeDao;
import com.hm.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/10/010 22:04
 */
@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee login(String sn, String password) {

        Employee employee = employeeDao.select(sn);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    public void changPwd(Employee employee) {
        employeeDao.update(employee);
    }
}
