package cn.itcast.order;

import cn.itcast.feign.clients.UserClient;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableFeignClients 开启 Feign 客户端功能。Feign 是一种声明式、模板化的 HTTP 客户端，可以方便地调用 HTTP API。
 */
@MapperScan("cn.itcast.order.mapper")
@EnableFeignClients(clients = UserClient.class)
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建RestTemplate对象并注入spring容器
     * @LoadBalanced Ribbon负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 设置负载均衡策略为随机 全局配置
     * @return
     */
//    @Bean
//    public IRule randomRule(){
//        return new RandomRule();
//    }

}