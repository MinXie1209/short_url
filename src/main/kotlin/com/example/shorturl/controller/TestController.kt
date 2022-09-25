package com.example.shorturl.controller

import com.alibaba.fastjson.JSONObject
import com.example.shorturl.service.LongShortUrlService

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class TestController {
//    @Autowired
//    private lateinit var redissonClient: RedissonClient

    @Resource
    private lateinit var longShortUrlService: LongShortUrlService

    @RequestMapping("/long/2/short")
    fun long2ShortUrl(@RequestParam("long_url") longUrl: String): Any {
        return JSONObject.toJSONString(longShortUrlService.long2ShortUrl(longUrl))
    }

    @RequestMapping("/a/{shortUrl}")
    fun short2Long(@PathVariable shortUrl: String): Any {
        return longShortUrlService.short2Long(shortUrl)
    }
}