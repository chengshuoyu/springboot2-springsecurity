/**
 * 
 */
package com.ycs.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.io.File;


/**
 *
 * @author chengshuoyu
 */
@SpringBootApplication
public class ApplicationIniter extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationIniter.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(ApplicationIniter.class);
	}

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.setDocumentRoot(new File("D:\\workspace\\study\\springboot2-springsecurity\\src\\main\\webapp"));
		return factory;
	}


}