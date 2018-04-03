package com.cn.wang.zl.wangmyappkotlin.listener

/**
 * Created by lcling on 2018/4/3.
 * 回调监听接口
 */
interface LoadTasksCallBack<in T> {

    fun onSuccess(t: T)
    fun onStart()
    fun onFailed()
    fun onFinish()
}