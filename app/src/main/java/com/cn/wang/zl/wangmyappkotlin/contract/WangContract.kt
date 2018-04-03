package com.cn.wang.zl.wangmyappkotlin.contract

import com.cn.wang.zl.wangmyappkotlin.base.BaseView
import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import retrofit2.Response

/**
 * Created by lcling on 2018/4/3.
 * 契约接口存放相同业务的Presenter和View
 */
interface WangContract {

    interface View : BaseView<Presenter> {
        //判断Fragment是否添加到了Activity
        val isActive: Boolean

        fun getWang(cmsBeanResponse: Response<CMSBean>)
        fun showLoading()
        fun hideLoading()
        fun showError()
    }

    interface Presenter {
        fun setWang(code: String)
        fun destroy()
    }
}