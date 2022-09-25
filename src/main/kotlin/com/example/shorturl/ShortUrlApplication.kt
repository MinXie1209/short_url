package com.example.shorturl

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.example.shorturl.mapper")
class ShortUrlApplication

fun main(args: Array<String>) {
    runApplication<ShortUrlApplication>(*args)
}
