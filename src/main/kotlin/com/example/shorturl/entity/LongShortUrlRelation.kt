package com.example.shorturl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("long_short_url_relation")
class LongShortUrlRelation {
    @TableId(type = IdType.AUTO)
    var id: Long = 0L
    var longUrl: String = ""
    var shortUrl: String = ""
}