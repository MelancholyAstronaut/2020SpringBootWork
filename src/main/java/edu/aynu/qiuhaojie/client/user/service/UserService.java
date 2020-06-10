package edu.aynu.qiuhaojie.client.user.service;

import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.User;

import java.util.List;


public interface UserService {

    int insertUser(User u);
    int activeUser(String code);
    User findUser(User user);

    User findUserName(User setUsername);

    int modifyUser(User user);

    List<Orders> findOrderById(Integer id);
}
