package edu.aynu.qiuhaojie.client.cart.controller;

import com.alipay.api.internal.util.AlipaySignature;
import edu.aynu.qiuhaojie.client.order.service.OrdersService;
import edu.aynu.qiuhaojie.client.products.service.ProductsService;
import edu.aynu.qiuhaojie.commons.beans.Products;
import edu.aynu.qiuhaojie.utils.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
/**
 * @author: qhj
 */
public class CartController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/addCart")
    public String addCart(Integer id, HttpSession session) {
        double total = 0L;
        Products product = productsService.findProductById(id);
        if (session.getAttribute("total") == null) {
            total += product.getPrice();
        } else {
            total = (double) session.getAttribute("total");
            total += product.getPrice();
        }
        Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
        }
        cart.merge(product, 1, Integer::sum);
        session.setAttribute("cart", cart);
        session.setAttribute("total", total);
        return "/client/cart";
    }

    @RequestMapping("/changeCart")
    public String changeCart(Integer id, Integer count, HttpSession session) {
        double total = 0;
        Products product = productsService.findProductById(id);
        Map<Products, Integer> cart = ((Map<Products, Integer>) session.getAttribute("cart"));
        if (count == 0) {
            cart.remove(product);
        } else {
            if (count > product.getPnum()) {
                count = product.getPnum();
            }
            cart.put(product, count);
        }
        Set<Products> set = cart.keySet();
        for (Products products : set) {
            total += products.getPrice() * cart.get(products);
        }
        session.setAttribute("total", total);
        return "/client/cart";
    }

    @RequestMapping("/pay")
    public String pay(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String order_id = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String money = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            ordersService.updatePayState(order_id);
        } else {
            return "/client/fail";
        }
        return "/client/paysuccess";
    }
}
