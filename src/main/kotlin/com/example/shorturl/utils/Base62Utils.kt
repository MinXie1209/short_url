package com.example.shorturl.utils

class Base62Utils {
    companion object {
        // 62个字符 10个数字+大小写英文字母
        private const val CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        // 字符数组长度
        private const val CHARS_LEN = CHARS.length

        fun genBase62(input: Long): String {
            var newInput = input
            val sb = StringBuilder("")
            if (newInput == 0L) {
                return "0"
            }
            while (newInput > 0) {
                newInput = genBase62(newInput, sb)
            }
            return sb.reverse().toString()
        }

        private fun genBase62(input: Long, sb: StringBuilder): Long {
            val rem = (input % CHARS_LEN).toInt()
            sb.append(CHARS[rem])
            return input / CHARS_LEN
        }

    }
}

fun main() {
    //922,3372,0368,5477,5807L
//    println(Base62Utils.fromBase10(Int.MAX_VALUE.toLong()))
//    println(Base62Utils.fromBase10(Long.MAX_VALUE))
    // 62 + 0
    // 1*62 +0
    // 1*21 + 1*20
    println(Base62Utils.genBase62(10000))
//    println(Base62Utils.fromBase10(328193123))
//    println(Base62Utils.fromBase10(3876123))
}