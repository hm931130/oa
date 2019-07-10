package com.hm.oa.controller;

import com.hm.oa.biz.GlobalBiz;
import com.hm.oa.entity.Employee;
import com.hm.oa.global.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/10/010 22:06
 */

@Controller("globalController")
public class GlobalController {


    @Autowired
    private GlobalBiz globalBiz;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession httpSession, @RequestParam String sn, @RequestParam String password) {
        //存在该用户并且密码输入正确
        if (globalBiz.login(sn, password) != null) {
            httpSession.setAttribute(Constants.SESSION_USER, globalBiz.login(sn, password));
            return "redirect:self";
        } else {
            return "redirect:to_login";
        }
    }

    @RequestMapping("/self")
    public String self() {
        return "self";
    }

    @RequestMapping("quit")
    public String quit(HttpSession session) {
        session.setAttribute(Constants.SESSION_USER, null);
        return "redirect:to_login";
    }

    @RequestMapping("/to_changepwd")
    public String toChangePwd() {
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession httpSession,@RequestParam String old,@RequestParam String new1,@RequestParam String new2){

        Employee employee = (Employee) httpSession.getAttribute(Constants.SESSION_USER);


        if (employee.getPassword().equals(old)){
            //两次输入的密码相同 允许操作
            if (new1.equals(new2)){
                employee.setPassword(new1);
                globalBiz.changPwd(employee);
                return "redirect:self";
            }else{
                return "redirect:to_changepwd";
            }
        }

        return "redirect:to_changepwd";
    }

}
