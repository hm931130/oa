package com.hm.oa.controller;

import com.hm.oa.biz.DepartmentBiz;
import com.hm.oa.biz.EmpolyeeBiz;
import com.hm.oa.entity.Department;
import com.hm.oa.entity.Employee;
import com.hm.oa.global.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO(部门控制器)
 * @Author Administrator
 * @Date 2019/7/7/007 1:22
 */
@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmpolyeeBiz empolyeeBiz;

    @Autowired
    private DepartmentBiz departmentBiz;


    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        map.put("list", empolyeeBiz.getAll());
        return "employee_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("employee", new Employee());
        List<Department> departments = departmentBiz.getAll();
        map.put("dlist", departments);
        map.put("plist", Constants.getPost());
        return "employee_add";
    }


    @RequestMapping("/add")
    public String add(Employee employee) {
        empolyeeBiz.add(employee);
        return "redirect:list";
    }


    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpdate(String sn, Map<String, Object> map) {
        map.put("employee", empolyeeBiz.get(sn));
        List<Department> departments = departmentBiz.getAll();
        map.put("dlist", departments);
        map.put("plist", Constants.getPost());
        return "employee_update";
    }

    @RequestMapping("/update")
    public String update(Employee employee) {
        empolyeeBiz.edit(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn) {
        empolyeeBiz.remove(sn);
        return "redirect:list";
    }

}
