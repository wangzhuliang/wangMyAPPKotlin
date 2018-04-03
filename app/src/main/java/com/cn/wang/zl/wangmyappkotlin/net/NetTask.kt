package com.cn.wang.zl.wangmyappkotlin.net

import com.cn.wang.zl.wangmyappkotlin.presenter.WangPresenter

/**
 * Created by lcling on 2018/4/3.
 */
interface NetTask<T> {

     fun execute(
             data: T,
             callBack: WangPresenter)

}