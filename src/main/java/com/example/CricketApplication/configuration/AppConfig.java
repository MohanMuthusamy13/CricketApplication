package com.example.CricketApplication.configuration;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.example.CricketApplication")
public class AppConfig {

    @Bean(name = "threadExecutorService")
    public Executor threadExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(100);
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(320);
        executor.setThreadNamePrefix("poolThread -");
        executor.initialize();
        return executor;
    }
}