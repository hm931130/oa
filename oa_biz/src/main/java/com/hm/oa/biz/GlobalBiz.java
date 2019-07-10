package com.hm.oa.biz;

import com.hm.oa.entity.Employee;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/10/010 22:03
 */
public interface GlobalBiz {


    Employee login(String sn, String password);

    void changPwd(Employee employee);

}
