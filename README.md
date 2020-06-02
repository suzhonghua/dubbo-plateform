# platform

#### 介绍
dubbo + zookeeper 微服务

#### 软件架构
mysql: 数据库
dubbo: RPC
zookeeper: 注册中心、动态配置中心
dubbo-admin: 监控中心
oauth2+jwt: 安全框架

plateform
├── api -- dubbo调用接口
├── auth -- 认证服务器
├── common -- 通用jar包
├── doc -- docker + sql
└── upms -- 统一权限管理

#### 使用说明
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/091252_248f22f3_6572634.jpeg "1591060336(1).jpg")

1.  docker 启动 docker-compose-env-dubbo.yml
2.  连接数据库 执行sql
3.  dubbo-admin 按照文档说的 zookeeper作为配置中心，dubbo-admin配置文件把注册中心去掉 否则没有元数据, 可以直接build镜像
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/091627_b5f5244c_6572634.png "6_}JUUDL[1NR603XO_M)X[N.png")
4.  ConsumerTokenFilter、ProviderTokenFilter token校验拦截
5.  EnableDubboConsumer、EnableDubboProvider、EnableSysLog注解加载dubbo配置文件
6.  SpringConfigurationFromZk项目启动先读取zookeeper配置中心的配置  否则读不到数据库配置
![zookeeper配置节点](https://images.gitee.com/uploads/images/2020/0602/092416_bfbf0cce_6572634.jpeg "1591061034(1).jpg")

#### 前端地址 https://gitee.com/s_szhua/platform-ui.git
基于vue-admin-template 简介 漂亮
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/100820_2b690589_6572634.jpeg "1591062099(1).jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/100832_7aa5131e_6572634.jpeg "1591062113(1).jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/100840_bbfb7652_6572634.jpeg "1591062131(1).jpg")
#### 问题

1.  dubbo-admin 添加配置文件时  global没有问题，application级别的  位置会乱，导致dubbo加载不到 可以使用其他可视化工具配置
2.  dubbo加载配置文件  根据文档  配置信息应该使用namespace隔离的  但是看源码 好像是 用的group 而且拼接成/dubbo/config/你的group
3.  dubbo文档：http://dubbo.apache.org/zh-cn/
以上只是个人问题 初来乍到，有问题加微信
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/093133_bf3d5bdc_6572634.jpeg "1591061034(1).jpg")
