package cn.wsgwz.miniproxy.proxy

import android.content.Context
import android.util.Log
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by admin on 2017/7/21 0021.
 */
class ProxyServer private constructor(): Thread() {

    val tag:String = ProxyServer.javaClass.toString();

    companion object {
        var state:Boolean = false;

        val port:Int = 8001;

        lateinit var proxyServer:ProxyServer;
        fun getInstance(): ProxyServer {
            if(proxyServer==null){
                synchronized(ProxyServer.javaClass){
                    if(proxyServer==null){
                        proxyServer = ProxyServer();
                    }
                }
            }
            return proxyServer;
        }
    }
    override fun run() {
        super.run()
        serverSocket = ServerSocket(port);
        excutorService = Executors.newCachedThreadPool();
        var socket:Socket = serverSocket.accept()
        while (socket!=null){

            excutorService.execute(Task(socket));
            socket=serverSocket.accept()
        }


    }

    lateinit var context:Context ;
    lateinit var excutorService:ExecutorService;

    lateinit var thread:Thread;

    lateinit var serverSocket:ServerSocket;

    fun start(context: Context){
        Log.d(tag,"start(context: Context)");
        if(thread==null) return

        this.context = context;
        thread = Thread(this);
        thread.start();
    }
    fun stopB(){
        Log.d(tag,"stopB()");
        serverSocket?.close();
        thread?.interrupt()
    }


}