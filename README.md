服务调用
ribbon
1.是一个负载均衡，服务调用的客户端工具
2.ribbon是本地负载均衡  进程式LB
  nginx是服务端负载均衡  集中式LB
3.提供多种策略:轮询  随机   群众
4.Eureka自带ribbon依赖
5.ribbon配置文件不能放在当前包及子包中
6.主启动类添加@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)

openfeign
1.就是个接口，并且在接口上添加注解即可


服务降级
1.hystrix是一种处理服务降级和服务熔断的开源库
2.服务降级 服务器忙，请稍后再试，给对方一个友好的提示 fallback
    那些情况下会降级  超时 程序运行异常  熔断触发降级 
  服务熔断 直接停止访问了  然后调用服务降级  break
  服务限流 秒杀高并发等操作 ，一秒n个 有序进行
  实时监控 
3.超时不再等待
  出错要有兜底
4.一般服务降级 fallback 放在客户端
一对一 在方法上服务降级  一对多  在类上服务降级
5. 服务熔断  发现服务节点相应正常是  恢复调用链路
  比如1秒钟100的并发量 500个请求干崩了 这时候有72次发过来了 就可以试试连接
  先服务降级  进而熔断  恢复链路
  熔断打开  请求不会再调用当前服务
  熔断关闭  不会对服务进行熔断
  熔断搬开	慢慢恢复调用链路
  
服务网关
gateway新一代网关
路由：构建网关的基本模块由ID，目标URL，一系列的断言和过滤器组成，断言为true，匹配该路由
断言 就是实现一组匹配规则，让请求过来找到对应的ROUTE处理
过滤

服务配置
服务太多  需要有个集中式的，动态的配置管理
分布式配置管理
bootstrap.yml 是系统级的是最高优先级 高于application.yml 是用户级的

消息总线 Bus
配合config实现动态刷新
1.利用消息总线触发一个客户端bus/refresh，刷新所有客户端的设置
2.利用消息总线触发一个服务端ConfigServer的bus/refresh端点，刷新所有客户端的设置
选择通知总控方式   进而全局通知  通知客户端可能会挂掉
一次修改  广播通知  处处生效 curl -X POST "http://localhost:3344/actuator/bus-refresh"
定点通知
 curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
 curl -X POST "http://localhost:分布式配置中心端口号/actuator/bus-refresh/微服务名称:需要定点通知的微服务实例端口号"

springcloud stream
可以不再关注mq的细节，用一种适配绑定的方式，在多个mq之间切换
binder(绑定器):input 消息生产者  消息消费者
发布-订阅模式  topic主题广播 在ribbit中是Exchange  在kafka中是topic
通过绑定器binder作为中间层，实现应用程序与消息中间件之间的隔离
binder 很方便的连接中间件
Channel 就相当于队列 实现存储转发
source sink  消息的输入输出 （生产者 消费者）
@EnableBinding(Source.class)  消息生产者的发送管道
分组
不同的组可以全面消费(重复消费)
同一个组内会发生竞争关系，只有其中一个可以消费。
故障现象 ：重复消费
导致原因 ：默认分组是不同的。组流水好不一样，被认为不同组，可以重复消费
同一个组只有一个可以消费
持久化
无分组属性配置，后台没有打出来消息
有分组属性配置，后台打出来了MQ上的消息
