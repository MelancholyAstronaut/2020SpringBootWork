package edu.aynu.qiuhaojie.client.order.service.impl;

import edu.aynu.qiuhaojie.client.order.dao.OrdersDao;
import edu.aynu.qiuhaojie.client.order.service.OrdersService;
import edu.aynu.qiuhaojie.commons.beans.OrderItem;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.Products;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2020-05-31 16:49:17
 */
@Transactional
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void CreatOrder(Orders orders, Map<Products, Integer> cart) {
        for (Products p : cart.keySet()) {
            OrderItem oit = new OrderItem().setBuynum(cart.get(p)).setOrder(orders).setProduct(p);
            ordersDao.insertOrderiItem(oit);
            ordersDao.updateProductNum(oit);
        }
        //int i = 1 / 0;

        ordersDao.insertOrder(orders);
    }

    @Override
    public void updatePayState(String order_id) {

        ordersDao.updatePayState(order_id);

    }
}