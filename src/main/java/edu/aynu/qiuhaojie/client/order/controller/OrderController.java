package edu.aynu.qiuhaojie.client.order.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import edu.aynu.qiuhaojie.client.order.service.OrdersService;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.Products;
import edu.aynu.qiuhaojie.commons.beans.User;
import edu.aynu.qiuhaojie.utils.AlipayConfig;
import edu.aynu.qiuhaojie.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/client/cart")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/creatOrder")
    public String creatOrder(Orders orders, HttpSession session, Model model) {
        orders.setPaystate(0);
        orders.setId(IdUtils.getUUID());
        User u = (User) session.getAttribute("login_user");
        orders.setUser(u);
        Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
        ordersService.CreatOrder(orders, cart);
        //System.out.println(orders);
        session.removeAttribute("cart");
        model.addAttribute("orders", orders);
        return "/client/confirm";
    }

    @RequestMapping("/pay")
    public void pay(String id, String money, HttpServletResponse response) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = id;
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html");
        response.getWriter().print(result);
    }
}
