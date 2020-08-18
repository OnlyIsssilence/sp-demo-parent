## spring cloud Alibaba 接入RocketMQ

### 本地win10上部署启动RocketMQ(具体参考文章5)
- 设置RocketMQ环境变量
- 启动nameserver:cmd下**start mqnamesrv.cmd**或者双击mqnamesrv.cmd 
- 启动broker:cmd下**start mqbroker.cmd -n localhost:9876**



## 参考文档
* [1.spring cloud alibaba RocketMQ Example](https://github.com/alibaba/spring-cloud-alibaba/tree/master/spring-cloud-alibaba-examples/rocketmq-example)
* [2.alibaba RocketMQ介绍:服务器部署主要文档](https://github.com/alibaba/spring-cloud-alibaba/wiki/RocketMQ)
* [3.启动耗内存解决方案:rocketMQ+centos+安装配置](https://blog.csdn.net/cdnight/article/details/81027829)
* [4.启动异常"No name server address, please set it"解决方案:mq启动异常 ](https://blog.csdn.net/huaxin_sky/article/details/80686896?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param)
* [5.windows上安装部署RocketMQ ](https://www.jianshu.com/p/eec2c813c4ff)
