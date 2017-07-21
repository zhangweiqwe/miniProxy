package cn.wsgwz.miniproxy.proxy

import java.io.InputStream
import java.io.OutputStream

/**
 * Created by admin on 2017/7/21 0021.
 */
class StreamToServer(var clientIn:InputStream,var remoteOut:OutputStream): Thread() {

    val buffer:ByteArray = kotlin.ByteArray(4096);
    override fun run() {
        super.run()
        var len:Int = -1;
        try {
            len = clientIn.read(buffer);
            while (len!=-1){
                remoteOut.write(buffer,0,len);
                len = clientIn.read(buffer);
            }
            remoteOut.flush()
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            clientIn?.close();
            remoteOut?.close()
        }


    }
}