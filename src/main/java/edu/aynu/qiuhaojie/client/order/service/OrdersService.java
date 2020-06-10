package edu.aynu.qiuhaojie.client.order.service;

import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.Products;

import java.util.Map;

/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2020-05-31 16:49:17
 */
public interface OrdersService {

    void CreatOrder(Orders orders, Map<Products, Integer> cart);

    void updatePayState(String order_id);
}