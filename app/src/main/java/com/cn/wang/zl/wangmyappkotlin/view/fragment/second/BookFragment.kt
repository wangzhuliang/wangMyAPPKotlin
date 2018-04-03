package com.cn.wang.zl.wangmyappkotlin.view.fragment.second

import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment

/**
 * Created by lcling on 2018/3/28.
 * homefragment
 */
open class BookFragment : BaseMainFragment(){

    object Singleton{
        operator fun invoke(s: String): BookFragment {
            val homeFragment = BookFragment()
            return homeFragment
        }
    }


}