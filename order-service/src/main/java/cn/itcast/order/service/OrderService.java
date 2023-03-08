package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用restTemplate发送Http请求
        // 2.1url
        String url = "http://user-service/user/" + order.getUserId();
        // 2.2发送http请求实现远程调用
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装user到order对象
        order.setUser(user);
        // 4.返回
        return order;
    }
}
