package edu.aynu.qiuhaojie.client.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PagesLink {
    @RequestMapping("/goRegister")
    public String goRegister() {
        return "/client/register";
    }

    @RequestMapping("/goback")
    public String goBack() {
        return "index";
    }

    @RequestMapping("/modifyuserinfo")
    public String modifyuserinfo() {
        return "/client/modifyuserinfo";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model) {
        model.addAttribute("message", "未登录, 请登录");

        return "/client/login";
    }

    @RequestMapping("/cart")
    public String ToCart() {
        return "/client/cart";
    }

    @RequestMapping("/goOrder")
    public String goOrder() {
        return "/client/order";
    }

    @RequestMapping("/goPaysuccess")
    public String goPaysuccess() {
        return "/client/paysuccess";
    }

}
