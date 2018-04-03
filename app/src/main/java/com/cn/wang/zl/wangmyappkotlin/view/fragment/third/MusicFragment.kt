package com.cn.wang.zl.wangmyappkotlin.view.fragment.third

import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment

/**
 * Created by lcling on 2018/3/28.
 * homefragment
 */
open class MusicFragment : BaseMainFragment(){

    object Singleton{
        operator fun invoke(s: String): MusicFragment {
            val homeFragment = MusicFragment()
            return homeFragment
        }
    }


}