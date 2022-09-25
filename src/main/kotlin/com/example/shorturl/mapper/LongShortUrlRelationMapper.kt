package com.example.shorturl.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.example.shorturl.entity.LongShortUrlRelation
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface LongShortUrlRelationMapper : BaseMapper<LongShortUrlRelation> {
    @Select("select id,long_url,short_url from long_short_url_relation where long_url=#{longUrl}")
    fun selectByLongUrl(@Param("longUrl") longUrl: String): LongShortUrlRelation?

    @Select("select id,long_url,short_url from long_short_url_relation where short_url=#{shortUrl}")
    abstract fun selectByShortUrl(@Param("shortUrl") shortUrl: String): LongShortUrlRelation?
}