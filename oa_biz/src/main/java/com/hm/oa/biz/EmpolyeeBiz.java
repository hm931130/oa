package com.hm.oa.biz;

import com.hm.oa.entity.Employee;

import java.util.List;

/**
 * @Description: TODO(封装业务方法)
 * @Author Administrator
 * @Date 2019/7/7/007 1:15
 */
public interface EmpolyeeBiz {

    void add(Employee employee);

    void edit(Employee employee);

    void remove(String sn);

    Employee get(String sn);

    List<Employee> getAll();
}
