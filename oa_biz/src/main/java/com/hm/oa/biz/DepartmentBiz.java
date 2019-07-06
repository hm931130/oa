package com.hm.oa.biz;

import com.hm.oa.entity.Department;

import java.util.List;

/**
 * @Description: TODO(封装业务方法)
 * @Author Administrator
 * @Date 2019/7/7/007 1:15
 */
public interface DepartmentBiz {

    void add(Department department);

    void edit(Department department);

    void remove(String sn);

    Department get(String sn);

    List<Department> getAll();
}
