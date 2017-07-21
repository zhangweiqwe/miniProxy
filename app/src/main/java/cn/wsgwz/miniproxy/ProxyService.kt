package cn.wsgwz.miniproxy

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by admin on 2017/7/21 0021.
 */
class ProxyService : Service() {

    var tag:String = ProxyService.javaClass.toString();

    enum class Action{
        stop,start
    }

    companion object {

        var state:Boolean = false;
        var actionKey:String = "ActionKey"
    }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent!!.extras.getSerializable(actionKey)){
            Action.stop->{

            }
            Action.start->{

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