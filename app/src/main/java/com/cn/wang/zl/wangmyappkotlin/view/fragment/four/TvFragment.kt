package com.cn.wang.zl.wangmyappkotlin.view.fragment.four

import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment

/**
 * Created by lcling on 2018/3/28.
 * homefragment
 */
open class TvFragment : BaseMainFragment(){

    object Singleton{
        operator fun invoke(s: String): TvFragment {
            val homeFragment = TvFragment()
            return homeFragment
        }
    }


}