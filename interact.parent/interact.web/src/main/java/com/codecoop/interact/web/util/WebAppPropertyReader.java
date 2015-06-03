package com.codecoop.interact.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(name = "webAppPropertyReader", value = {"classpath:application.properties"})
public class WebAppPropertyReader {

    @Autowired
    private Environment env;

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
}
