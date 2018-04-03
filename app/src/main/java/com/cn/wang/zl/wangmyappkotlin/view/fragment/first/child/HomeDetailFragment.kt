package com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cn.wang.zl.wangmyappkotlin.R
import com.cn.wang.zl.wangmyappkotlin.base.BaseBackFragment
import com.cn.wang.zl.wangmyappkotlin.bean.Article
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child.HomeDetailFragment.wang.ARG_ITEM

/**
 * Created by lcling on 2018/4/3.
 */
class HomeDetailFragment : BaseBackFragment(){

    object wang{
        ////用object修饰，相当于Java中的static，用object修饰一个变量，可以实现全局变量的效果
        val ARG_ITEM: String = "arg_item"
    }

    private var mArticle: Article? = null

    private lateinit var mToolbar: Toolbar
    private lateinit var mImgDetail: ImageView
    private lateinit var mTvTitle: TextView
    private lateinit var mFab: FloatingActionButton

    object Singleton{
        operator fun invoke(article: Article, _mActivity: FragmentActivity): HomeDetailFragment {
            val args = Bundle()
            args.putParcelable(ARG_ITEM, article)
            val fragment = HomeDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mArticle = arguments!!.getParcelable(ARG_ITEM)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_detail, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        mToolbar = view.findViewById(R.id.toolbar)
        mImgDetail = view.findViewById(R.id.img_detail)
        mTvTitle = view.findViewById(R.id.tv_content)
        mFab = view.findViewById(R.id.fab)

        mToolbar.title = ""
        initToolbarNav(mToolbar)
        Glide.with(_mActivity).load(mArticle!!.content).into(mImgDetail)
        //mImgDetail.setImageResource(mArticle.getImgRes());
        mTvTitle.text = mArticle!!.title

        mFab.setOnClickListener { start(CycleFragment.Singleton(1)) }
    }
}