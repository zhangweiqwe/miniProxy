package cn.wsgwz.miniproxy

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        package_uid_TV.text = android.os.Process.myUid().toString();
        if(ProxyService.Companion.state==false) switch_Bn.setText(R.string.start) else switch_Bn.setText(R.string.stop)
        switch_Bn.setOnClickListener{
            if(ProxyService.Companion.state==false){
                ProxyService.Companion.state = true;
                switch_Bn.setText(R.string.stop)
            }else{
                ProxyService.Companion.state = false;
                switch_Bn.setText(R.string.start)
            }
            setProxyServiceState(ProxyService.Companion.state);
        }


        var layoutMannager: LinearLayoutManager = LinearLayoutManager(this);
        log_RV.layoutManager = layoutMannager;
        log_RV.adapter = LogAdapter(this);

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

}
