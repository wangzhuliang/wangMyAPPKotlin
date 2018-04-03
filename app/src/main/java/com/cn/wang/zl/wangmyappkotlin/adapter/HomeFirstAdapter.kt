package com.cn.wang.zl.wangmyappkotlin.adapter

import android.support.constraint.R.id.parent
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cn.wang.zl.wangmyappkotlin.R
import com.cn.wang.zl.wangmyappkotlin.bean.Article
import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import com.cn.wang.zl.wangmyappkotlin.listener.OnItemClickListener
import java.util.ArrayList

/**
 * Created by lcling on 2018/4/3.
 * homefirstadapter
 */
class HomeFirstAdapter(val _mActivity: FragmentActivity, val urlImage: String?, val urlText: String?, val posts: List<CMSBean.PostsBean>?)
    : RecyclerView.Adapter<HomeFirstAdapter.VH>() {

    private var mClickListener: OnItemClickListener? = null
    private val mItems = ArrayList<Article>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        ViewCompat.setTransitionName(holder.img, position.toString() + "_image")
        ViewCompat.setTransitionName(holder.tvTitle, position.toString() + "_tv")

        Glide.with(_mActivity).load("http://cms.youlin365.com" + posts!![position].image).into(holder.img)
        holder.tvTitle.text = posts[position].title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(_mActivity).inflate(R.layout.item_home_first, parent, false)
        val holder = VH(view)
        holder.itemView.setOnClickListener { v ->
            val position = holder.adapterPosition
            if (mClickListener != null) {
                mClickListener!!.onItemClick(position, v, holder, "http://cms.youlin365.com" + posts!![position].image)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return posts!!.size
    }

    fun setDatas(items: List<Article>) {
        mItems.clear()
        mItems.addAll(items)
    }

    fun getItem(position: Int): Article {
        return mItems[position]
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var img: ImageView = itemView.findViewById(R.id.img)

    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mClickListener = itemClickListener
    }

}