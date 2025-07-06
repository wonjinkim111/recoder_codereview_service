package com.yaas.recodercodereviewservice;

import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableFeignClients
public class RecoderCodereviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecoderCodereviewServiceApplication.class, args);
	}

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean FilterRegistrationBean() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.setAllowedHeaders(Arrays.asList("Authorization", "Accept", "Content-Type", "Origin", "XSRF-TOKEN", "X-XSRF-TOKEN", "X-Requested-With"));
        config.addAllowedMethod("*");
        List<String> allowHeaderList = Arrays.asList("x-xsrf-token", "Origin", "Accept", "Content-Type", "X-Requested-With", "Access-Control-Allow-Method", "Access-Control-Allow-Headers", "Access-Control-Request-Method", "Access-Control-Request-Headers");
        config.setExposedHeaders(allowHeaderList);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean((Filter)new CorsFilter((CorsConfigurationSource)source), new org.springframework.boot.web.servlet.ServletRegistrationBean[0]);
        bean.setOrder(0);
        return bean;
    }
}
