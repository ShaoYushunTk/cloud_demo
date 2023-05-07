package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Yushun Shao
 * @date 2023/5/7 22:55
 * @description: AuthorizeFilter
 * @Order 指定过滤器顺序
 * implements GlobalFilter 实现全局过滤器，拦截所有进入网关的请求或者从微服务返回的响应，并且可以自定义处理逻辑
 */
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        // 2.获取参数中的authorization参数
        String auth = params.getFirst("authorization");
        // 3.判断参数是否等于admin
        if("admin".equals(auth)){
            // 4.是，放行
            return chain.filter(exchange);
        }
        // 5.否，拦截
        // 5.1设置状态码为未登录（未认证）
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 5.2拦截请求
        return exchange.getResponse().setComplete();
    }
}
