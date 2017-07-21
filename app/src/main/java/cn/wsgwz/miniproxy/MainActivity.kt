package cn.wsgwz.miniproxy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cn.wsgwz.miniproxy.proxy.ProxyServer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object {
        val logAction:String = "cn.wsgwz.miniproxy";
        val logTagMsg = "msg";
        fun log(context: Context,msg:String):Unit{
            val intent:Intent = Intent(logAction);
            intent.putExtra(logTagMsg,msg);
            context.sendBroadcast(intent)
        }
    }

    val logReceiver:LogReceiver = LogReceiver();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        package_uid_TV.text = android.os.Process.myUid().toString();
        if(ProxyServer.Companion.state==false) switch_Bn.setText(R.string.start) else switch_Bn.setText(R.string.stop)
        switch_Bn.setOnClickListener{
            if(ProxyServer.Companion.state==false){
                ProxyServer.Companion.state = true;
                switch_Bn.setText(R.string.stop)
            }else{
                ProxyServer.Companion.state = false;
                switch_Bn.setText(R.string.start)
            }
            setProxyServiceState(ProxyServer.Companion.state);
        }


        var layoutMannager: LinearLayoutManager = LinearLayoutManager(this);
        log_RV.layoutManager = layoutMannager;
        log_RV.adapter = LogAdapter(this);

        var logIntentFilter:IntentFilter = IntentFilter();
        logIntentFilter.addAction(Companion.logAction);
        registerReceiver(logReceiver,logIntentFilter);

    }

    fun setProxyServiceState(state:Boolean):Unit{
        var intent:Intent = Intent(this,ProxyService.javaClass);
        if(state){
            intent.putExtra(ProxyService.Companion.actionKey,ProxyService.Action.start)
        }else{
            intent.putExtra(ProxyService.Companion.actionKey,ProxyService.Action.stop)
        }
        startService(intent);
    }



    class LogReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            LogAdapter.list.add(p1?.getStringExtra(Companion.logTagMsg)?:"null pointer");
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(logReceiver);
    }

}
