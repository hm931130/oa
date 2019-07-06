package com.hm.oa.dao;

import com.hm.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/7/007 1:03
 */
@Repository("departmentDao")
public interface DepartmentDao {

    void insert(Department department);

    void update(Department department);

    void delete(String sn);

    Department select(String sn);

    List<Department> selectAll();
}
