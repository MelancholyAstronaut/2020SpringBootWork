package edu.aynu.qiuhaojie.client.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.aynu.qiuhaojie.client.user.dao.UserDao;
import edu.aynu.qiuhaojie.commons.beans.Orders;
import edu.aynu.qiuhaojie.commons.beans.User;
import edu.aynu.qiuhaojie.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int insertUser(User u) {
        String address = " <a href='http://localhost:8080/user/activeUser?code=" + u.getActivecode() + "'>这里</a>";
        String message = "请点击" + address + "激活用户";
        try {
            MailUtils.sendMail(u.getEmail(), message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.insertUser(u);
    }

    @Override
    public int activeUser(String code) {
        return userDao.activeUser(code);
    }

    @Override
    public User findUser(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", user.getUsername());
        qw.eq("password", user.getPassword());
        return userDao.selectOne(qw);
    }

    @Override
    public User findUserName(User u) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", u.getUsername());
        return userDao.selectOne(qw);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateById(user);
    }

    @Override
    public List<Orders> findOrderById(Integer id) {
        return userDao.findOrderById(id);
    }
}
