package com.example.shorturl.config;

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConf {

    @Bean
    fun redissonConfig(): Config {
        val config = Config()
        val singleServerConfig = config.useSingleServer();
        singleServerConfig.address = "redis://127.0.0.1:6379";
        return config;
    }

    @Bean
    fun redissonClient(): RedissonClient {
        return Redisson.create(redissonConfig());
    }
}