package com.ilya.ivanov.slides.config;

import lombok.val;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Configuration
public class AdditionalWebConfig extends WebMvcConfigurerAdapter {
    /**
     * Allowing all origins, headers and methods here is only intended to keep this example simple.
     * This is not a default recommended configuration. Make adjustments as
     * necessary to your use case.
     *
     */
    @Bean
    @Profile("dev")
    public FilterRegistrationBean corsFilter() {
        val source = new UrlBasedCorsConfigurationSource();
        val config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        val bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    @Profile("prod")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
