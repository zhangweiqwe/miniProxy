package cn.wsgwz.miniproxy.proxy

import java.io.InputStream

/**
 * Created by admin on 2017/7/21 0021.
 */
class HttpRequestHeader : RequestHeader() {

    override fun readHeader(clientIn: InputStream): RequestHeader?{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPort(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHost(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHeader(): StringBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMethod(): Method {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}