package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.net.URL;

/**
 * 类说明：统一的跨域访问配置
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 16:38
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.addAllowedHeader("*");
        conf.addAllowedMethod("*");//任意方式访问：GET POST DELETE PUT
        conf.addAllowedOriginPattern("*");
        conf.setAllowCredentials(true);//允许携带cookie
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return new CorsWebFilter(source);
    }
}
