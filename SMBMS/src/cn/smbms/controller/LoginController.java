package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);
    @Resource
    private UserService userService;
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/dologin.html")
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session){
        logger.info("doLogin====================================");
        User user = userService.login(userCode,userPassword);
        if (user!=null){
            session.setAttribute(Constants.USER_SESSION,user);
            return "redirect:/sys/main.html";//拦截器生效
        }else {
            request.setAttribute("error", "用户名或密码不正确");
            return "login";
        }
    }
    @RequestMapping(value="/sys/main.html")
    public String main(){
        return "frame";
    }
    @RequestMapping("/logout.html")
    public String logout(HttpSession session){
        logger.info("logout====================================");
        //清除session
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }
}
