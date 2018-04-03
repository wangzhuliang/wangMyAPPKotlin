package com.cn.wang.zl.wangmyappkotlin.base

import android.content.Context
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.HomeFragment
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by lcling on 2018/3/26.
 * 懒加载
 */
open class BaseMainFragment : SupportFragment() {

    private var _mBackToFirstListener : OnBackToFirstListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnBackToFirstListener) {
            _mBackToFirstListener = context
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");*/
        }
    }

    override fun onDetach() {
        super.onDetach()
        _mBackToFirstListener = null
    }

    /**
     * 处理回退事件
     *
     */
    override fun onBackPressedSupport(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            popChild()
        } else {
            if (this is HomeFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish()
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener!!.onBackToFirstFragment()
            }
        }
        return true
    }

    interface OnBackToFirstListener {
        fun onBackToFirstFragment()
    }
}