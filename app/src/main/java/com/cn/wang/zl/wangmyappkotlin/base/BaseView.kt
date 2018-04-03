package com.cn.wang.zl.wangmyappkotlin.base

/**
 * Created by lcling on 2018/4/3.
 */
interface BaseView<in T> {
    /**将presenter实例传入view中，
     * 其调用时机是presenter实现类的构造函数中。 */
    fun setPresenter(presenter: T)
}