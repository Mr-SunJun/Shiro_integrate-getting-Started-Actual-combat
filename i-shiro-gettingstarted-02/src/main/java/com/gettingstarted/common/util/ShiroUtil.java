package com.gettingstarted.common.util;

import com.gettingstarted.sys.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author SunJun
 */
public class ShiroUtil {
     
     /**
      * 初始化shiro的运行环境
      */
     static {
          //1、初始化shiro的安全管理
          DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
          
          //2、设置用户的权限信息到安全管理器，也就是权限管理
           //Realm realm = new IniRealm("classpath:shiro.ini");
          Realm realm = new ShiroRealm();
          defaultSecurityManager.setRealm(realm);
          
          //3、使用SecurityUtil工具，运行defaultSecurityManager
         SecurityUtils.setSecurityManager(defaultSecurityManager);
     }
     
     public static Subject login(String username,String password){
          //1,使用SecurityUtils创建一个Subject认证的主体
          Subject subject = SecurityUtils.getSubject();
     
          /**
           *  2.创建用于认证的Token认证器，记录用户认证的身份和凭证即账号和密码
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
          AuthenticationToken usernamePasswordToken =
               new UsernamePasswordToken(username, password);
          
          //3.主体进行登录，登录的时候进行认证检查
          subject.login(usernamePasswordToken);
          return subject;
     }
     
}
