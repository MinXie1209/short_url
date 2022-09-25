package com.example.shorturl.service

import com.example.shorturl.entity.LongShortUrlRelation
import com.example.shorturl.mapper.LongShortUrlRelationMapper
import com.example.shorturl.utils.Base62Utils
import org.redisson.api.RAtomicLong
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.servlet.view.RedirectView
import javax.annotation.PostConstruct
import javax.annotation.Resource

interface LongShortUrlService {
    fun long2ShortUrl(longUrl: String): String
    fun short2Long(shortUrl: String): Any
}

@Service
class LongShortUrlServiceImpl : LongShortUrlService {
    @Resource
    private lateinit var redissonClient: RedissonClient

    private lateinit var atomicLong: RAtomicLong

    @Value("\${base.path}")
    lateinit var basePath: String

    @PostConstruct
    fun init() {
        // redis 自增
        atomicLong = redissonClient.getAtomicLong("longUrlId")
    }

    @Resource
    private lateinit var longShortUrlRelationMapper: LongShortUrlRelationMapper
    override fun long2ShortUrl(longUrl: String): String {
        val longShortUrlRelation = longShortUrlRelationMapper.selectByLongUrl(longUrl)
        return if (longShortUrlRelation == null) {
            val newId = atomicLong.incrementAndGet()
            val newLongShortUrlRelation = LongShortUrlRelation().apply {
                this.longUrl = longUrl
                this.shortUrl = "${basePath}${Base62Utils.genBase62(newId)}"
            }
            longShortUrlRelationMapper.insert(newLongShortUrlRelation)
            newLongShortUrlRelation.shortUrl
        } else {
            longShortUrlRelation.shortUrl
        }
    }

    override fun short2Long(shortUrl: String): Any {
        val longShortUrlRelation = longShortUrlRelationMapper.selectByShortUrl("$basePath$shortUrl")
        if (longShortUrlRelation == null) {
            return ""
        }
        val redirectView = RedirectView(longShortUrlRelation.longUrl, true)
        redirectView.setStatusCode(HttpStatus.FOUND)
        return redirectView
    }

}