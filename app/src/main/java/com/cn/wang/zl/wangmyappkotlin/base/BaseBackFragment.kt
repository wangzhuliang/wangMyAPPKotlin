package com.cn.wang.zl.wangmyappkotlin.base

import android.support.v7.widget.Toolbar
import android.view.View
import com.cn.wang.zl.wangmyappkotlin.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by lcling on 2018/4/3.
 */
open class BaseBackFragment : SupportFragment(){

    protected fun initToolbarNav(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { _mActivity.onBackPressed() }
    }
}