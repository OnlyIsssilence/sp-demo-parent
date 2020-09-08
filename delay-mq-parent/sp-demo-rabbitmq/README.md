## springboot 接入rabbit MQ 实现延迟消费

### 1.名词解释
- 交换器 exchange: 消息首先到达mq中的地方
- 队列 queue: 消息最终落地mq的地方
- 路由键 routingKey: 消息到达exchange之后怎么知道需要去哪个队列中，就是根据路由键去匹配
- 绑定：在rabbitmq中，队列和交换器是需要绑定的，绑定的时候需要指定前面说的路由键
- TTL(time to live):  RabbitMQ可以针对Queue设置x-expires 或者 针对Message设置 x-message-ttl，来控制消息的生存时间，如果超
时(两者同时设置以最先到期的时间为准)，则消息变为dead letter(死信) RabbitMQ针对队列中的消息过期时间有两种方法可以设置。
  > A:  通过队列属性设置，队列中所有消息都有相同的过期时间。 
  > B:  对消息进行单独设置，每条消息TTL可以不同。 如果同时使用，则消息的过期时间以两者之间TTL较小的那个数值为准。消息在队列的
  生存时间一旦超过设置的TTL值，就成为dead letter
- DLX(dead letter exchanges):RabbitMQ的Queue可以配置x-dead-letter-exchange和x-dead-letter-routing-key（可选）两个参数，
如果队列内出现了dead letter，则按照这两个参数重新路由转发到指定的队列。 x-dead-letter-exchange：出现dead letter之后将dead 
letter重新发送到指定exchange x-dead-letter-routing-key：出现dead letter之后将dead letter重新按照指定的routing-key发送 
 队列出现dead letter的情况有：
> 消息或者队列的TTL过期 
> 队列达到最大长度 
> 消息被消费端拒绝（basic.reject or basic.nack）并且requeue=false

### 2.Exchange种类
> rabbitmq中分为四种交换器，分别为Direct, Fanout, Topic, Header 下面分别简述一下
- Direct 直接交换器，顾名思义，就是单一的路由规则，rabbitmq内置了一个Default Exchange 就是Direct类型的，当你申明一个队列未绑
定交换器时，rmq会自动将该交换器绑定到默认的交换器，并指定路由键就是队列的名字;
- Fanout 广播交换器，可以理解为一个消息到达该交换器后，会由该交换器将消息广播到所有绑定该交换器的队列中，且队列绑定时不需要指定路由键
- Topic 通配符交换器，可以理解为该队列存在的意义就是可以将多种路由键的消息通配到一个队列中，比如你申明一个Topic 交换器，同时申
明一个队列为ErrorQueue，绑定交换器时指定路由键为*.log，则所有生产的消息指定的路由键为a.log,b.log,c.log的消息都会进入该ErrorQueue队列。
- Header 此交换器已经基本不再使用

### 3.延时队列实现方案
- 设置了TTL规则之后当消息在一个队列中变成死信时，利用DLX特性它能被重新转发到另一个Exchange或者Routing Key，这时候消息就可以重新被消费了

### 4.启动mq
- windows:sbin/rabbitmq-server.bat
- linux:参考 **CentOS7.X下安装RabbitMQ(配合5设置开机自启动)**

### 5.消息确认与重发
发送消息确认：用来确认生产者 producer 将消息发送到 broker ，broker 上的交换机 exchange 再投递给队列 queue的过程中，消息是否成功投递
- 消息从 producer 到 rabbitmq broker有一个 confirmCallback 确认模式。
- 消息从 exchange 到 queue 投递失败有一个 returnCallback 退回模式。
- 我们可以利用这两个Callback来确保消的100%送达。
#### 5.1 消息发送确认
- ConfirmCallback确认模式：消息只要被 rabbitmq broker 接收到就会触发 confirmCallback回调，实现接口 ConfirmCallback ，
重写其confirm()方法。但消息被 broker 接收到只能表示已经到达 MQ服务器，并不能保证消息一定会被投递到目标 queue 里。所以接下
来需要用到 returnCallback
-  ReturnCallback 退回模式：如果消息未能投递到目标 queue 里将触发回调 returnCallback ，一旦向 queue 投递消息未成功，这里
一般会记录下当前消息的详细投递数据，方便后续做重发或者补偿等操作，实现接口ReturnCallback，重写 returnedMessage() 方法
#### 5.2 消息接收确认
消息接收确认要比消息发送确认简单一点，因为只有一个消息回执（ack）的过程。使用@RabbitHandler注解标注的方法要增加 channel
(信道)、message 两个参数。消费消息有三种回执方法，我们来分析一下每种方法的含义
- basicAck:表示成功确认，使用此回执方法后，消息会被rabbitmq broker 删除。
> deliveryTag：表示消息投递序号，每次消费消息或者消息重新投递后，deliveryTag都会增加。手动消息确认模式下，我们可以对指定
deliveryTag的消息进行ack、nack、reject等操作。
> multiple：是否批量确认，值为 true 则会一次性 ack所有小于当前消息 deliveryTag 的消息。
- basicNack:表示失败确认，一般在消费消息业务异常时用到此方法，可以将消息重新投递入队列
> deliveryTag：表示消息投递序号。
> multiple：是否批量确认。
> requeue：值为 true 消息将重新入队列。
- basicReject:拒绝消息，与basicNack区别在于不能进行批量操作，其他用法很相似
> deliveryTag：表示消息投递序号;
> requeue：值为 true 消息将重新入队列



## 参考文章
* [1.windows10环境下的RabbitMQ安装步骤（图文）](https://www.cnblogs.com/saryli/p/9729591.html)
* [2.使用rabbitmq 实现延迟消费](https://blog.csdn.net/zhuchunyan_aijia/article/details/80243454)
* [3.RabbitMQ 延迟队列](https://github.com/shuyuan1992/spring-boot-rabbitmq)
* [4.CentOS7.X下安装RabbitMQ(配合5设置开机自启动)](https://www.cnblogs.com/fengyumeng/p/11133924.html)
* [5.CentOS7.X设置rabbit自动启动](https://blog.csdn.net/u010289197/article/details/100759639)
* [6.Springboot + RabbitMQ 用了消息确认机制，感觉掉坑里了！](https://mp.weixin.qq.com/s/GL7MnQZJcl4Ff34XT00Kgg)
* [7.RabbitMQ系列优秀博文文章](https://www.cnblogs.com/haixiang/p/10826710.html)