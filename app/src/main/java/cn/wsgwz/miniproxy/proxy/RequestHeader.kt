package cn.wsgwz.miniproxy.proxy

import java.io.InputStream

/**
 * Created by admin on 2017/7/21 0021.
 */
abstract class RequestHeader {

    abstract fun readHeader(clientIn:InputStream):RequestHeader?
    abstract fun getPort():Int
    abstract fun getHost():String
    abstract fun getHeader():StringBuilder
    abstract fun getMethod():Method

    enum class Method{
        CONNECT,GET,POST
    }
}