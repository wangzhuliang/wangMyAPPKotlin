package com.cn.wang.zl.wangmyappkotlin.presenter

import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import com.cn.wang.zl.wangmyappkotlin.contract.WangContract
import com.cn.wang.zl.wangmyappkotlin.listener.LoadTasksCallBack
import com.cn.wang.zl.wangmyappkotlin.model.WangTask
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child.HomeFirstFragment
import retrofit2.Response

/**
 * Created by lcling on 2018/4/3.
 * 实现Presenter接口
 */
class WangPresenter(var addTaskView: HomeFirstFragment?, val wangTask: WangTask) : WangContract.Presenter,LoadTasksCallBack<Response<CMSBean>>{


    override fun onSuccess(t: Response<CMSBean>) {
        addTaskView!!.getWang(t)
    }

    override fun onStart() {
        addTaskView!!.showLoading()
    }

    override fun onFailed() {
        addTaskView!!.showError()
        addTaskView!!.hideLoading()
    }

    override fun onFinish() {
        addTaskView!!.hideLoading()
    }

    override fun setWang(code: String) {
        this.wangTask.execute(code,this)
    }

    override fun destroy() {
        addTaskView = null
    }
}