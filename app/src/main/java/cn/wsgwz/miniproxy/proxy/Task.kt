package cn.wsgwz.miniproxy.proxy

import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

/**
 * Created by admin on 2017/7/21 0021.
 */
class Task(val socketClient:Socket):Runnable{
    var clientIn:InputStream
    var clientOut:OutputStream
    init {
       clientIn = socketClient.getInputStream()
       clientOut = socketClient.getOutputStream()
    }

    companion object {
        val connectOk:ByteArray  = "HTTP/1.1 200 Connection Established\r\n\r\n".toByteArray();
    }
    override fun run() {
        var socketRemote:Socket? = null;
        try {
            var requestHeader:RequestHeader? = HttpRequestHeader().readHeader(clientIn)
            if(requestHeader==null){
                return;
            }
            socketRemote = Socket(requestHeader.getHost(),requestHeader.getPort())

            val remoteOut:OutputStream = socketRemote.getOutputStream();
            val remoteIn:InputStream = socketRemote.getInputStream();


            when(requestHeader.getMethod()){
                RequestHeader.Method.GET->{
                    remoteOut.write(requestHeader.getHeader().toString().toByteArray())
                    remoteOut.flush()
                }
                RequestHeader.Method.POST->{
                    remoteOut.write(requestHeader.getHeader().toString().toByteArray())
                    remoteOut.flush()
                }
                RequestHeader.Method.CONNECT->{
                    clientOut.write(connectOk)
                    clientOut.flush()
                }
            }

            var  streamToServer:StreamToServer = StreamToServer(clientIn,remoteOut);
            var  streamToClient:StreamToClient = StreamToClient(remoteIn,clientOut);

            streamToServer.start()
            streamToClient.start()
            streamToServer.join()
            streamToClient.join()


        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            socketClient?.close()
            socketRemote?.close()
        }

    }
}