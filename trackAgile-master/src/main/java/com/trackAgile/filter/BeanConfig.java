package com.trackAgile.filter;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
