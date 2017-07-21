package cn.wsgwz.miniproxy

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var loglist:ArrayList<String> = ArrayList<String>();
    lateinit var logAdapter:LogAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        package_uid_TV.text = getCurrentUid(this)?.toString();
        switch_Bn.setOnClickListener{

        }


        loglist?.add("demo");
        logAdapter = LogAdapter(loglist,this);
        var layoutMannager: LinearLayoutManager = LinearLayoutManager(this);
        log_RV.layoutManager = layoutMannager;
        log_RV.adapter = logAdapter;



        loglist?.add("demo3");
        logAdapter?.notifyDataSetChanged();

    }


    //获取uid
    fun getCurrentUid(context:Context):Int?{
        var packageManager:PackageManager = context?.packageManager;
        var applicationInfo:ApplicationInfo = packageManager.getApplicationInfo(context.packageName,PackageManager.GET_ACTIVITIES);
        return applicationInfo?.uid?:null;
    }
}
