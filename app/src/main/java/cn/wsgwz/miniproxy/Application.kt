package cn.wsgwz.miniproxy

import android.app.ActivityManager
import android.app.Application
import android.content.Context

/**
 * Created by admin on 2017/7/21 0021.
 */
class Application: Application() {
    companion object {
        var packageName:String = "cn.wsgwz.miniproxy";
    }
    override fun onCreate() {
        super.onCreate()
        if(getCurrentProgressName(this).equals(Companion.packageName)){

        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun getCurrentProgressName(context: Context):String{
        var activityManager:ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
        for(processes in activityManager.runningAppProcesses){
            if(processes.pid == android.os.Process.myPid()){
                return processes.processName;
            }
        }
        return "";
    }
}