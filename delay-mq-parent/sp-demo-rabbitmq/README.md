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


## 参考文章
* [1.windows10环境下的RabbitMQ安装步骤（图文）](https://www.cnblogs.com/saryli/p/9729591.html)
* [2.使用rabbitmq 实现延迟消费](https://blog.csdn.net/zhuchunyan_aijia/article/details/80243454)
* [3.RabbitMQ 延迟队列](https://github.com/shuyuan1992/spring-boot-rabbitmq)
* [4.CentOS7.X下安装RabbitMQ(配合5设置开机自启动)](https://www.cnblogs.com/fengyumeng/p/11133924.html)
* [5.CentOS7.X设置rabbit自动启动](https://blog.csdn.net/u010289197/article/details/100759639)