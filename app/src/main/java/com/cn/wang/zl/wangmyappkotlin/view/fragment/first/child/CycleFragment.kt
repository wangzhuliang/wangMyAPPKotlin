package com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cn.wang.zl.wangmyappkotlin.R
import com.cn.wang.zl.wangmyappkotlin.base.BaseBackFragment
import com.cn.wang.zl.wangmyappkotlin.bean.Article
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child.CycleFragment.wang.ARG_NUMBER

/**
 * Created by lcling on 2018/4/3.
 * aaaa
 *
 */
class CycleFragment : BaseBackFragment() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTvName: TextView
    private lateinit var mBtnNext: Button
    private lateinit var mBtnNextWithFinish: Button
    var mNumber: Int = 0

    object wang{
        ////用object修饰，相当于Java中的static，用object修饰一个变量，可以实现全局变量的效果
        val ARG_NUMBER: String = "arg_number"
    }

    object Singleton{
        operator fun invoke(number: Int): CycleFragment {
            val fragment = CycleFragment()
            val args = Bundle()
            args.putInt(ARG_NUMBER, number)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cycle, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        mToolbar = view.findViewById(R.id.toolbar)
        mTvName = view.findViewById(R.id.tv_name)
        mBtnNext = view.findViewById(R.id.btn_next)
        mBtnNextWithFinish = view.findViewById(R.id.btn_next_with_finish)

        val title = "CyclerFragment " + mNumber

        mToolbar.title = title
        initToolbarNav(mToolbar)

        mTvName.text = title
        mBtnNext.setOnClickListener { start(CycleFragment.Singleton(mNumber + 1)) }
        mBtnNextWithFinish.setOnClickListener { startWithPop(CycleFragment.Singleton(mNumber + 1)) }
    }
}