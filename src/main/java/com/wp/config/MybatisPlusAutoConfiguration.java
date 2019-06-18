package com.wp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @description: mybatis-puls配置
 * @author: zhanjian
 * @date: 2019年6月18日 下午7:05:40
 * @version: v1.0
 */
@Configuration
public class MybatisPlusAutoConfiguration {

    /**
     * @description: 分页拦截器
     * @return
     * @throws
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
