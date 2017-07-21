package cn.wsgwz.miniproxy.proxy

import java.io.InputStream
import java.io.OutputStream

/**
 * Created by admin on 2017/7/21 0021.
 */
class StreamToClient (var remoteIn: InputStream, var clientOut: OutputStream): Thread() {

    val buffer:ByteArray = kotlin.ByteArray(4096);
    override fun run() {
        super.run()
        var len:Int = -1;
        try {
            len = remoteIn.read(buffer);
            while (len!=-1){
                clientOut.write(buffer,0,len);
                len = remoteIn.read(buffer);
            }
            clientOut.flush()
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            remoteIn?.close();
            clientOut?.close()
        }


    }
}