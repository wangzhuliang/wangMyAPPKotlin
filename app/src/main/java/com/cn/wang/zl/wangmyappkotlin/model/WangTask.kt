package com.cn.wang.zl.wangmyappkotlin.model

import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import com.cn.wang.zl.wangmyappkotlin.net.GetRequest_Interface
import com.cn.wang.zl.wangmyappkotlin.net.NetTask
import com.cn.wang.zl.wangmyappkotlin.presenter.WangPresenter
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by lcling on 2018/4/3.
 *  NetTask实现类,获取数据
 */
class WangTask : NetTask<String> {

    private var Instance: WangTask? = null

    fun getInstance(): WangTask? {
        if (Instance == null) {
            Instance = WangTask()
        }
        return Instance
    }

    override fun execute(data: String, callBack: WangPresenter) {

        //网络请求
        callBack.onStart()

        /**
         * retrofit2网络请求
         */
        val builder = OkHttpClient.Builder()
        onHttps(builder)
        //创建Retrofit对象
        val retrofit = Retrofit.Builder() //设置baseUrl
                .baseUrl("https://cms.youlin365.com/ghost/api/v0.1/") // 设置 网络请求 Url//添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)//设置OKHttpClient,如果不设置会提供一个默认的
                .client(builder.build())
                .build()

        // 创建 网络请求接口 的实例
        val request = retrofit.create<GetRequest_Interface>(GetRequest_Interface::class.java)

        //对 发送请求 进行封装
        val call = request.getCall()

        //call只能调用一次。否则会抛 IllegalStateException
        //Call<List<Repo>> clone = call.clone();

        //发送网络请求(异步)
        call.enqueue(object : Callback<CMSBean> {
            //请求成功时候的回调
            override fun onResponse(call: Call<CMSBean>, response: Response<CMSBean>) {
                //请求处理,输出结果

                //val urlImage = "http://cms.youlin365.com" + response.body()!!.posts!![0].image
                //val urlName = "http://cms.youlin365.com" + response.body()!!.posts!![0].meta_title.toString()
                //val urlText = "http://cms.youlin365.com" + response.body()!!.posts!![0].title

                callBack.onSuccess(response)
                //callBack.onFailed();
            }

            //请求失败时候的回调
            override fun onFailure(call: Call<CMSBean>, throwable: Throwable) {
                callBack.onFailed()
                //System.out.println("连接失败"+throwable.toString());
            }

        })
    }

    @Throws(Exception::class)
    fun getSSLSocketFactory(): SSLSocketFactory {
        //创建一个不验证证书链的证书信任管理器。
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts,
                java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .socketFactory
    }


    //使用自定义SSLSocketFactory
    private fun onHttps(builder: OkHttpClient.Builder) {
        try {
            builder.sslSocketFactory(getSSLSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}