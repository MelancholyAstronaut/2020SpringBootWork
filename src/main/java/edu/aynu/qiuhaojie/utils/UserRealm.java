package edu.aynu.qiuhaojie.utils;

import edu.aynu.qiuhaojie.client.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("======>执行了授权步骤");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("======>执行了认证步骤");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        System.out.println(userToken.getUsername() + "=====" + userToken.getPassword());
        return new SimpleAuthenticationInfo("", userToken.getPassword(), "");
    }
}
