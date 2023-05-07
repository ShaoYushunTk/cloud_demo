package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author Yushun Shao
 * @date 2023/5/7 21:05
 * @description: feign configuration
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.BASIC;
    }
}
