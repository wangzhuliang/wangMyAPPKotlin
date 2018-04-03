package com.cn.wang.zl.wangmyappkotlin.listener

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by lcling on 2018/4/3.
 */
open class OnItemClickListener {

    open fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder, image: String) {}
}