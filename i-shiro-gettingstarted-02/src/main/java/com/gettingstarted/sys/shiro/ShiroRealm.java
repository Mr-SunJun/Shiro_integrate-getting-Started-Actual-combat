package com.gettingstarted.sys.shiro;


import com.gettingstarted.sys.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author SunJun
 */
public class ShiroRealm extends AuthorizingRealm {
     
     /**
      * 登录认证
      * 这个authenticationToken是ShiroUtil类中的subject.login(Token)函数中的Token值
      */
     @Override
     protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
          UsernamePasswordToken token = (UsernamePasswordToken)  authenticationToken;
          //1.获取用户输入的用户名
          String username = token.getUsername();
          //2.获取用户输入的密码
          String password = new String(token.getPassword());
     
          //3.根据用户名去mysql数据库中查询用户信息，模拟测试，手动输入的与实体类中的参数对比，模拟数据库的判断
          User user = new User("admin","123456");
          if(!user.getUusername().equals(username)){
               throw new UnknownAccountException("用户名不存在或用户名错误");
          }
          if(!user.getPassword().equals(password)){
               throw new IncorrectCredentialsException("密码不存在或密码错误");
          }
          System.out.println("认证成功...");
          //创建简单认证信息对象
          SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
          return info;
     }
     
     /**
      *授权
      * 将认证通过的用户的角色和权限信息设置到对应的用户主体上
      */
     @Override
     protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
          
          //PrincipalCollection principals 用户的主体身份信息,主要获取用户的身份信息
          String username = principals.getPrimaryPrincipal().toString();
          //模拟从数据库获取当前用户的角色,通过用户查询该用户拥有的角色名称
          Set<String> roleNameSet = new HashSet<>();
          roleNameSet.add("系统管理员");
          roleNameSet.add("系统运维");
          
          //模拟从数据库当前用户的权限,通过用户名查询该用户拥有的权限名称
          Set<String> permissionNameSet = new HashSet<>();
          permissionNameSet.add("sys:user:list"); //查看列表
          permissionNameSet.add("sys:user:info"); //查看用户详情
          permissionNameSet.add("sys:user:create");//创建用户
          permissionNameSet.add("sys:user:update");//修改用户
          permissionNameSet.add("sys:user:delete");//删除用户
          
          //简单授权信息对象,对象包含用户的角色和权限信息
          SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
          info.addRoles(roleNameSet);
          info.addStringPermissions(permissionNameSet);
          
          System.out.println("授权完成...");
          return info;
     }
}
