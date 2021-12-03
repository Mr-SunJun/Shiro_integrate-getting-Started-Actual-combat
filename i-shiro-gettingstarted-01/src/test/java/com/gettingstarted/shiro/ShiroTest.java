package com.gettingstarted.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.junit.Test;

public class ShiroTest {
     
     @Test
    public void test(){
          //1、初始化shiro的安全管理
          DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
          //2、设置用户的权限信息到安全管理器，也就是权限管理
          IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
          defaultSecurityManager.setRealm(iniRealm);
          //3、使用SecurityUtil工具，运行defaultSecurityManager
          SecurityUtils.setSecurityManager(defaultSecurityManager);
     }
     
}
