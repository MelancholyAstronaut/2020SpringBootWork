package edu.aynu.qiuhaojie.client.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.qiuhaojie.commons.beans.OrderItem;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * (Orders)表数据库访问层
 * @author qhj
 * @since 2020-05-31 16:49:16
 */
@Mapper
@Component
public interface OrdersDao extends BaseMapper<Orders> {
    void insertOrder(Orders orders);
    void insertOrderiItem(OrderItem oit);

    void updateProductNum(OrderItem oit);

    void updatePayState(String id);
}