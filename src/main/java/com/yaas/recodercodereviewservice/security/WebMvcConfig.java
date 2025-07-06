package com.yaas.recodercodereviewservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		    registry.addResourceHandler(new String[] { "/**" }).addResourceLocations(new String[] { "file:///C:/recoder/recoder-img" });
		  }
}
