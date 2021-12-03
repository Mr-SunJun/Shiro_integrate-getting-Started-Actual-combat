::: hljs-center
# Shiro_integrate_gettingStarted_Actualcombat
## 目录结构(记录每天Maven项目)
:::

 ## i-shiro-gettingstarted-01(第一天)
```
详情讲解请进入到这个i-shiro-gettingstarted-01这个项目中
|-- i-shiro-gettingstarted-01
        |-- pom.xml (pom中使用的依赖有，junit(测试依赖)，log4j-slf4j-impl(桥接log4j与slf4j依赖),shiro-core(权限依赖核心依赖之一))
        |-- .idea
        |   |-- .gitignore
        |   |-- compiler.xml
        |   |-- jarRepositories.xml
        |   |-- misc.xml
        |   |-- runConfigurations.xml
        |   |-- workspace.xml
        |   |-- codeStyles
        |   |   |-- codeStyleConfig.xml
        |   |   |-- Project.xml
        |   |-- inspectionProfiles
        |       |-- Project_Default.xml
        |-- src
        |   |-- main
        |   |   |-- java
        |   |   |-- resources
        |   |       |-- log4j2.xml (log4j2日志配置文件，里面配置的输出到控制台的日志格式以什么格式) 
        |   |       |-- shiro.ini （shiro.ini文件是配置用户权限信息其中包括新增查看修改导入导出操作配置文件）
        |   |-- test
        |       |-- java
        |           |-- com
        |               |-- gettingstarted
        |                   |-- shiro
        |                       |-- ShiroTest.java (ShiroTest这个类使用了SecurityUtils测试shiro的环境是否搭建成功)
```

