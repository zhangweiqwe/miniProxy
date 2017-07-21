package cn.wsgwz.miniproxy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by admin on 2017/7/21 0021.n
 */
class LogAdapter (context: Context): RecyclerView.Adapter<LogAdapter.ViewHolder>() {

    var tag:String = LogAdapter.javaClass.toString();

    companion object {
        var list: ArrayList<String> = ArrayList()


    }


    var inflater:LayoutInflater;
    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title_TV?.setText(list?.elementAt(position));
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.view_log_adapter_item,parent,false));
    }

    override fun getItemCount(): Int {
        Log.d(tag,""+list?.size?:"0");
        return list?.size?:0;
    }

    class ViewHolder: RecyclerView.ViewHolder {
        var title_TV:TextView;
        constructor( itemView:View) : super(itemView) {
            title_TV = itemView.findViewById<TextView>(R.id.title_TV);
        }
    }


}