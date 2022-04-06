package com.hzj.web;

import com.google.gson.Gson;
import com.hzj.pojo.User;
import com.hzj.service.UserService;
import com.hzj.service.impl.UserServiceImpl;
import com.hzj.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author hzj
 * @create 2022-02-27 10:02
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();



    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.getSession().invalidate();
           resp.sendRedirect(req.getContextPath());

    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User login = userService.login(WebUtils.copyParamToBean(req.getParameterMap(),new User()));
        if(login == null){
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }else{
            System.out.println("登录成功");
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp); ;
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String token =(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);



        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2. 检查验证码是否正确
        if(token != null && token.equalsIgnoreCase(code)){
            // 2.1 正确
            // 3 检查用户名是否可用
            if(userService.existUsername(username)){
                // 3.2 不可用---> 跳回注册页面
                System.out.println("用户名["+username+"]已存在");

                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                // 3.1 可用 ---> 调用Service保存到数据库，并跳到注册成功页面
                userService.registUser(user);
                System.out.println("注册成功");
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else{
            //2.2 不正确---> 跳回注册页面
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }



    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        boolean existUsername = userService.existUsername(username);
        Map<String, Object> resultMap= new HashMap<>();
        resultMap.put("existsUsername", existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }

}
