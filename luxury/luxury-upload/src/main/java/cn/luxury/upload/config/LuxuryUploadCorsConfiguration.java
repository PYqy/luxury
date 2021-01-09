package cn.luxury.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class LuxuryUploadCorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {

        //初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许这个域名跨域访问,如果携带cookir 不能写*,*：代表所有域名都可以跨域访问
        corsConfiguration.addAllowedOrigin("http://manage.luxury.com");
        corsConfiguration.setAllowCredentials(true);
        //代表所有的请求方法
        corsConfiguration.addAllowedMethod("*");
        //允许携带任何头信息
        corsConfiguration.addAllowedHeader("*");

        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return  new CorsFilter(corsConfigurationSource);
    }

}
