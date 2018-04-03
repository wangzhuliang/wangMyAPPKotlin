package com.cn.wang.zl.wangmyappkotlin.view.fragment.first.child

import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.view.*
import android.widget.AdapterView
import com.cn.wang.zl.wangmyappkotlin.R
import com.cn.wang.zl.wangmyappkotlin.adapter.HomeFirstAdapter
import com.cn.wang.zl.wangmyappkotlin.bean.Article
import com.cn.wang.zl.wangmyappkotlin.bean.CMSBean
import com.cn.wang.zl.wangmyappkotlin.contract.WangContract
import com.cn.wang.zl.wangmyappkotlin.helper.DetailTransition
import com.cn.wang.zl.wangmyappkotlin.listener.OnItemClickListener
import com.cn.wang.zl.wangmyappkotlin.model.WangTask
import com.cn.wang.zl.wangmyappkotlin.presenter.WangPresenter
import com.helper.loadviewhelper.load.LoadViewHelper
import me.yokeyword.eventbusactivityscope.EventBusActivityScope
import me.yokeyword.fragmentation.SupportFragment
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by lcling on 2018/4/3.
 * homefirstFragment
 */
open class HomeFirstFragment : SupportFragment() ,SwipeRefreshLayout.OnRefreshListener,WangContract.View{

    private lateinit var mRecy : RecyclerView
    private lateinit var mRefreshLayout : SwipeRefreshLayout

    private var wangPresenter: WangPresenter? = null
    private var mPresenter: WangContract.Presenter? = null

    private lateinit var helper: LoadViewHelper

    private var mAdapter: HomeFirstAdapter? = null

    private var urlImage: String? = null
    private var urlText: String? = null

    object Singleton{
        operator fun invoke(): HomeFirstFragment {
            val homeFirstFragment = HomeFirstFragment()
            return homeFirstFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_first, container, false)
//        EventBusActivityScope.getDefault(_mActivity).register(this)

        initView(view)

        val wangTask = WangTask()
        wangPresenter = WangPresenter(this@HomeFirstFragment, wangTask)

        val window = activity!!.window
        //默认API 最低19，状态栏透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val contentView = window.decorView.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
        contentView.getChildAt(0).fitsSystemWindows = false
        return view
    }

    private fun initView(view: View) {

        mRecy = view.findViewById(R.id.recy)
        mRefreshLayout = view.findViewById(R.id.refresh_layout)
        helper = LoadViewHelper(view.findViewById(R.id.aaa))

        helper.setListener { mPresenter!!.setWang("code") }
    }

    /**
     * 在onResume()中，调用了 presenter 得start()方法，获取数据并操作view界面的显示。
     */
    override fun onResume() {
        super.onResume()
        setPresenter(this.wangPresenter!!)
    }

    override fun onRefresh() {
        mRefreshLayout.postDelayed({
            //刷新
            mRefreshLayout.isRefreshing = false
            mPresenter!!.setWang("code")
        }, 2000)
    }

    override fun setPresenter(presenter: WangContract.Presenter) {
        this.mPresenter = presenter
        mPresenter!!.setWang("code")
    }


    override fun getWang(cmsBeanResponse: Response<CMSBean>) {
            helper.showContent()
            urlImage = "http://cms.youlin365.com" + cmsBeanResponse.body()!!.posts!![0].image
            urlText = cmsBeanResponse.body()!!.posts!![0].title

            mAdapter = HomeFirstAdapter(_mActivity, urlImage, urlText, cmsBeanResponse.body()!!.posts)
            val manager = LinearLayoutManager(_mActivity)
            mRecy.layoutManager = manager
            mRecy.adapter = mAdapter

            mAdapter!!.setOnItemClickListener(object : OnItemClickListener() {
                override fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder, image: String) {
                    val fragment = HomeDetailFragment.Singleton(mAdapter!!.getItem(position),_mActivity)

                    // 这里是使用SharedElement的用例
                    // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
                        exitTransition = Fade()
                    fragment.enterTransition = Fade()
                    fragment.sharedElementReturnTransition = DetailTransition()
                    fragment.sharedElementEnterTransition = DetailTransition()

                        // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                        // 25.1.0+的support包，SharedElement正常
                        extraTransaction()
                                .addSharedElement((vh as HomeFirstAdapter.VH).img, getString(R.string.image_transition))
                                .addSharedElement((vh as HomeFirstAdapter.VH).tvTitle, "tv")
                                .start(fragment)

                        /*extraTransaction().addSharedElement(((HomeFirstAdapter.VH) vh).img,image)
                                .addSharedElement(((HomeFirstAdapter.VH) vh).tvTitle, "tv")
                                .start(fragment);*/
                }
            })

            // Init Datas
        val articleList = (0 until cmsBeanResponse.body()!!.posts!!.size).map {
            //int index = i % 5;
            Article(cmsBeanResponse.body()!!.posts!![it].title!!,
                    "http://cms.youlin365.com" + cmsBeanResponse.body()!!.posts!![it].image)
        }
        mAdapter!!.setDatas(articleList)
    }

    override fun showLoading() {
        helper.showLoading()
    }

    override fun hideLoading() {
    }

    override fun showError() {
        helper.showError()
    }

    override val isActive: Boolean
        get() = isAdded

    override fun onDestroy() {
        super.onDestroy()
        //EventBusActivityScope.getDefault(_mActivity).unregister(this)
        if (mPresenter != null) {
            mPresenter!!.destroy()
            mPresenter = null
        }
    }
}