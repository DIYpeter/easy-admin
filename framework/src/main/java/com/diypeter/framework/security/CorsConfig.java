package com.diypeter.framework.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:43
 */
@Configuration
public class CorsConfig {

    /**
     * 一般情况是通过@WebFilter来标记一个filter，这里通过spring的 FilterRegistrationBean 来注册我们自己的过滤器
     * 使用 FilterRegistrationBean 可以配置过滤器的优先级
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter());
        // 设置过滤器的优先级为最高
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.getUrlPatterns();
        return registrationBean;
    }

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader(CorsConfiguration.ALL);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }
}
