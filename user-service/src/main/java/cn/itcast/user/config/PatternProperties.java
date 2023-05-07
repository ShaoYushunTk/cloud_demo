package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yushun Shao
 * @date 2023/5/7 20:01
 * @description: pattern properties
 * @ConfigurationProperties 将配置文件中的属性值映射到 Java 对象中，方便统一管理配置属性, prefix指定属性前缀,实现nacos配置更改后微服务热更新
 * 配置文件优先级：服务名-profile.yaml > 服务名.yaml > 本地配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat;
    private String envSharedValue;
    private String name;
}
