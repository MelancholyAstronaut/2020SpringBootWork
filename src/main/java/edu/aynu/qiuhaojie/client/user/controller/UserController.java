package edu.aynu.qiuhaojie.client.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import edu.aynu.qiuhaojie.client.user.service.UserService;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.User;
import edu.aynu.qiuhaojie.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    private UserService userService;

    @RequestMapping("/imageCode")
    public void getImage(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("verifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping("/register")
    public String registerUser(User u, String imageCode, Model model, HttpServletRequest request) throws MessagingException {
        //先验证验证码
        u.setActivecode(IdUtils.getUUID());
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        if (StringUtils.equalsIgnoreCase(verifyCode, imageCode)) {
            System.out.println("系统显示的" + verifyCode);
            System.out.println("你输入的" + imageCode);
            //相等
            System.out.println(u);
            userService.insertUser(u);
            return "client/registersuccess";
        } else {
            model.addAttribute("message", "验证码错误");
            return "client/register";
        }
    }

    @RequestMapping("activeUser")
    public String activeUser(String code) {
        //System.out.println(code);
        int row = userService.activeUser(code);
        if (row > 0) {
            return "/client/activesuccess";
        } else {
            return "/client/activefail";
        }
    }

    @RequestMapping("/find_username")
    @ResponseBody
    public String find_username(String username) {
        User u = userService.findUserName(new User().setUsername(username));
        if (u != null) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping("/myAccount")
    public String login(HttpSession session, Model model, HttpServletRequest request) {
        User u = (User) session.getAttribute("login_user");
        if (u == null) {
            u = auto_login(request);
            if (u == null) {
                model.addAttribute("message", "你还未登录,请登录");
                return "/client/login";
            }
        }
        session.setAttribute("login_user", u);
        return "/client/myAccount";
    }

    private User auto_login(HttpServletRequest request) {
        String username = null;
        String password = null;

        Cookie[] cookie = request.getCookies();
        for (Cookie c : cookie) {
            if (StringUtils.equalsIgnoreCase(c.getName(), "bookstore_password")) {
                password = c.getValue();
            }
            if (StringUtils.equalsIgnoreCase(c.getName(), "bookstore_name")) {
                username = c.getValue();
            }
        }
        User u = new User().setUsername(username).setPassword(password);
        u = userService.findUser(u);
        return u;
    }

    @RequestMapping("/login")
    public String login(User user, Model model,
                        HttpSession session,
                        String remember, HttpServletResponse response,
                        HttpServletRequest request,
                        String auto_login) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        User login_user = userService.findUser(user);
        if (login_user == null) {
            model.addAttribute("message", "用户名或者密码错误");
            return "/client/login";
        } else {
            if (login_user.getState() == 0) {
                model.addAttribute("message", "用户未激活");
                return "/client/login";
            }
            if (StringUtils.equalsIgnoreCase(auto_login, "1")) {
                addCookie(auto_login, user, request, response);
            } else if (StringUtils.equalsIgnoreCase(remember, "1")) {
                //记住用户名
                addCookie(auto_login, user, request, response);
            }
            session.setAttribute("login_user", login_user);

            return "/client/myAccount";
        }
    }

    private void addCookie(String auto_login, User user, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.equalsIgnoreCase(auto_login, "1")) {
            Cookie cookie = new Cookie("bookstore_password", user.getPassword());
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath(request.getContextPath() + "/");
            response.addCookie(cookie);
        }
        Cookie cookie = new Cookie("bookstore_name", user.getUsername());
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath(request.getContextPath() + "/");
        response.addCookie(cookie);

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session,
                         Model model,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        model.addAttribute("message", "你已成功退出");
        Cookie cookie = new Cookie("bookstore_name", null);
        Cookie cookie2 = new Cookie("bookstore_password", null);
        cookie.setPath(request.getContextPath() + "/");
        cookie2.setPath(request.getContextPath() + "/");
        cookie.setMaxAge(0);
        cookie2.setMaxAge(0);
        response.addCookie(cookie);
        response.addCookie(cookie2);
        session.removeAttribute("login_user");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            System.out.println("subject 已经授权, 现在推出");
            subject.logout();
        }

        return "/client/login";
    }

    @RequestMapping("/modify_user")
    public String modify_user(User user, HttpSession session, Model model) {
        User login_user = ((User) session.getAttribute("login_user"));
        user.setId(login_user.getId());
        int row = userService.modifyUser(user);
        if (row > 0) {
            model.addAttribute("message", "用户信息修改成功,请重新登录");
            return "/client/login";
        } else {
            model.addAttribute("message", "用户信息修改失败");
            return "client/modifyuserinfo";
        }

    }

    @RequestMapping("/findOrderByUser")
    public String findOrderByUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("login_user");
        List<Orders> orders = userService.findOrderById(user.getId());
        model.addAttribute("orders", orders);
        return "/client/orderlist";
    }
}