package com.cn.wang.zl.wangmyappkotlin.view.fragment.five

import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment

/**
 * Created by lcling on 2018/3/28.
 * homefragment
 */
open class GameFragment : BaseMainFragment(){

    object Singleton{
        operator fun invoke(s: String): GameFragment {
            val homeFragment = GameFragment()
            return homeFragment
        }
    }


}