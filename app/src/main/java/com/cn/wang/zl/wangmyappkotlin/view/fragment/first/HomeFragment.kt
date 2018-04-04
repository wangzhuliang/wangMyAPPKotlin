package com.cn.wang.zl.wangmyappkotlin.view.fragment.first

import android.os.Build
import android.os.Bundle
import android.view.*
import com.cn.wang.zl.wangmyappkotlin.R
import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child.HomeFirstFragment
import me.yokeyword.eventbusactivityscope.EventBusActivityScope

/**
 * Created by lcling on 2018/3/28.
 * homefragment wang
 */
open class HomeFragment : BaseMainFragment(){

    object Singleton{
        operator fun invoke(s: String): HomeFragment {
            val homeFragment = HomeFragment()
            return homeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        if (findChildFragment(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.fl_first_container, HomeFirstFragment.Singleton())
        }
    }

}