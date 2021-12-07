package com.gettingstarted.shiro;

import com.gettingstarted.common.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {
     
     @Test
    public void test(){
          //1、初始化shiro的安全管理
          DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
          //2、设置用户的权限信息到安全管理器，也就是权限管理
          Realm iniRealm = new IniRealm("classpath:shiro.ini");
          defaultSecurityManager.setRealm(iniRealm);
          //3、使用SecurityUtil工具，运行defaultSecurityManager
          SecurityUtils.setSecurityManager(defaultSecurityManager);
          
          //4,使用SecurityUtils创建一个Subject认证的主体
          Subject subject = SecurityUtils.getSubject();
     
          /**
           *  5、创建用于认证的Token认证器，记录用户认证的身份和凭证即账号和密码
           *   账号和密码错误时则会抛出如下异常。
           *
           *   该异常是账号错误
           *   org.apache.shiro.authc.UnknownAccountException: Realm [org.apache.shiro.realm.text.IniRealm@35ef1869]
           *   was unable to find account data for the submitted AuthenticationToken
           *   [org.apache.shiro.authc.UsernamePasswordToken - zhangsansz, rememberMe=false].
           *
           *   该异常是密码错误
           *   org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token
           *   [org.apache.shiro.authc.UsernamePasswordToken - zhangsan, rememberMe=false]
           *   did not match the expected credentials.
           */
          AuthenticationToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123456");
     
          //用户认证状态
          System.out.println("未登录前用户认证状态:"+subject.isAuthenticated());
          //6，主体进行登录，登录的时候进行认证检查
          subject.login(usernamePasswordToken);
          //用户认证状态
          System.out.println("登录后用户认证状态:"+subject.isAuthenticated());
          
          //7.检查角色的授权状态
          System.out.println("是否拥有admin角色:"+subject.hasRole("admin"));
          System.out.println("是否拥有admin角色:"+subject.hasRole("public"));
     
          //8.检查权限的授权状态
          System.out.println("是否拥admin有某个权限"+subject.isPermitted("product:view"));
          System.out.println("是否拥admin有某个权限"+subject.isPermitted("product:view1"));
     
          //9.主体信息
          System.out.println("用户名:"+subject.getPrincipal());
          
          //10.主体退出
          subject.logout();
          //退出后主体信息为null
          System.out.println("用户名:"+subject.getPrincipal());
        
     }
     
     @Test
     public void test02(){
          //登录
        Subject subject =  ShiroUtil.login("admin","123456");
          //授权资源检查
          
          //模拟当前用户点击了<新增用户>按钮,检查该用户是否拥有新增用户的权限
          System.out.println("检查该用户是否拥有新增用户的权限:"+subject.isPermitted("sys:user:create"));
          //模拟当前用户点了<新增角色>按钮,检查该用户是否拥有新增角色的权限
          System.out.println("检查该用户是否拥有新增用户的权限:"+subject.isPermitted("sys:role:create"));
          
          //检查当前用户是什么角色
          System.out.println("检查该用户是否是系统管理员角色:"+subject.hasRole("系统管理员"));
          System.out.println("检查该用户是否是系统运维角色:"+subject.hasRole("系统运维"));
     
     
          //退出系统
          subject.logout();
     }
}
