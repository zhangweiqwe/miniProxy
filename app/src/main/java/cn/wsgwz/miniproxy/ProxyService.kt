package cn.wsgwz.miniproxy

import android.app.Service
import android.content.Intent
import android.os.IBinder
import cn.wsgwz.miniproxy.proxy.ProxyServer

/**
 * Created by admin on 2017/7/21 0021.
 */
class ProxyService : Service() {

    val tag:String = ProxyService.javaClass.toString();

    enum class Action{
        stop,start
    }

    companion object {
        var actionKey:String = "ActionKey"
    }

    lateinit var proxyServer:ProxyServer;

    override fun onCreate() {
        super.onCreate()
        proxyServer = ProxyServer.getInstance();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent!!.extras.getSerializable(actionKey)){
            Action.stop->{
                proxyServer.stopB()
            }
            Action.start->{
                proxyServer.start(this)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder {
        return null!!;
    }
}