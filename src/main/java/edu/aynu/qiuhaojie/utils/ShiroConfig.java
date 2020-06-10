package edu.aynu.qiuhaojie.utils;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("webManager") DefaultWebSecurityManager d) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        /**
         * shiro的内置过滤器
         * anon ===>无需认证就可访问
         * authc ===>必须认证
         * user ====>拥有记住我
         * perms===>某个资源
         * role =>某个角色
         */

        bean.setSecurityManager(d);
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/page/modifyuserinfo", "authc");
        filterMap.put("/cart/addCart", "authc");
        filterMap.put("/page/cart", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/page/toLogin");
        return bean;
    }

    //defaultWebSecurityManager
    @Bean(name = "webManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //关联realm
        manager.setRealm(userRealm);
        return manager;
    }

    //realm对象
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
