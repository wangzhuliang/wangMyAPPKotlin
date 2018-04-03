package com.cn.wang.zl.wangmyappkotlin.net

import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by lcling on 2018/4/3.
 * http
 */
interface GetRequest_Interface {

    @GET("posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f" + "&" + "filter=tags:ying-yang-wen-zhang" + "&" +
            "limit=10" + "&" +
            "page=1")
    fun getCall(): Call<CMSBean>
}