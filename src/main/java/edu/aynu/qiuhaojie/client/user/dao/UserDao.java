package edu.aynu.qiuhaojie.client.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao extends BaseMapper<User> {
    int insertUser(User u);


    int activeUser(String code);

    List<Orders> findOrderById(Integer id);
}
