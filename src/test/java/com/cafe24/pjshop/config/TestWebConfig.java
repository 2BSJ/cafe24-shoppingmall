package com.cafe24.pjshop.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.MVCConfig;

import com.cafe24.config.web.SwaggerConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.pjshop.controller","com.cafe24.pjshop.exception"})
@Import({MVCConfig.class,SwaggerConfig.class})
public class TestWebConfig {
	

}

