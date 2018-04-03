package com.cn.wang.zl.wangmyappkotlin

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.Gravity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.cn.wang.zl.wangmyappkotlin.base.BaseMainFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.first.HomeFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.five.GameFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.four.TvFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.second.BookFragment
import com.cn.wang.zl.wangmyappkotlin.view.fragment.third.MusicFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : SupportActivity(), BottomNavigationBar.OnTabSelectedListener,BaseMainFragment.OnBackToFirstListener{

    val FIRST = 0
    val SECOND = 1
    val THIRD = 2
    val FOURTH = 3
    val FIVE = 4

    private val mFragments = arrayOfNulls<SupportFragment>(5)

    private lateinit var bottomNavigationBar : BottomNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar)
        //设置导航栏模式
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
        //设置导航栏背景模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
        //BACKGROUND_STYLE_RIPPLE
        //BACKGROUND_STYLE_STATIC,加上这个字体颜色可以修改
        val numberBadgeItem = TextBadgeItem()
        val shapeBadgeItem = ShapeBadgeItem()

        //添加标记
        numberBadgeItem.setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("1")
                .setHideOnSelect(true)
        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.yellow)
                .setGravity(Gravity.TOP or Gravity.END)
                .setHideOnSelect(true)

        bottomNavigationBar
                .addItem(BottomNavigationItem(R.mipmap.iv_assess_cut, "Home")
                        .setActiveColorResource(R.color.purple))
                //.setInActiveColorResource(R.color.deep_gray)
                .addItem(BottomNavigationItem(R.mipmap.iv_assess_fridge, "Books").setActiveColorResource(R.color.pink))
                .addItem(BottomNavigationItem(R.mipmap.iv_assess_modification, "Music").setActiveColorResource(R.color.light_blue))
                .addItem(BottomNavigationItem(R.mipmap.iv_assess_record, "Movies")
                        .setActiveColorResource(R.color.light_yellow).setBadgeItem(numberBadgeItem))
                .addItem(BottomNavigationItem(R.mipmap.iv_add, "Games")
                        .setActiveColorResource(R.color.orange).setBadgeItem(shapeBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise()

        //MODE_FIXED+BACKGROUND_STYLE_STATIC效果
        //MODE_FIXED+BACKGROUND_STYLE_RIPPLE效果
        //MODE_SHIFTING+BACKGROUND_STYLE_STATIC效果
        //MODE_SHIFTING+BACKGROUND_STYLE_RIPPLE效果

        //fragments = getFragments();
        //setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this)

        val firstFragment = findFragment(HomeFragment::class.java)
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.Singleton("Home")
            mFragments[SECOND] = BookFragment.Singleton("Books")
            mFragments[THIRD] = MusicFragment.Singleton("Music")
            mFragments[FOURTH] = TvFragment.Singleton("Movies")
            mFragments[FIVE] = GameFragment.Singleton("Games")

            loadMultipleRootFragment(R.id.layFrame, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIVE])
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment
            mFragments[SECOND] = findFragment(BookFragment::class.java)
            mFragments[THIRD] = findFragment(MusicFragment::class.java)
            mFragments[FOURTH] = findFragment(TvFragment::class.java)
            mFragments[FIVE] = findFragment(GameFragment::class.java)
        }
    }


    override fun onTabReselected(position: Int) {
        showHideFragment(mFragments[position])
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabSelected(position: Int) {
        val currentFragment = mFragments[position]
        val count = currentFragment!!.childFragmentManager.backStackEntryCount

        // 如果不在该类别Fragment的主页,则回到主页;
        if (count > 1) {
            (currentFragment as? HomeFragment)?.popToChild(HomeFragment::class.java, false) ?:
                    ((currentFragment as? BookFragment)?.popToChild(BookFragment::class.java, false) ?:
                            ((currentFragment as? MusicFragment)?.popToChild(MusicFragment::class.java, false) ?:
                                    ((currentFragment as? TvFragment)?.popToChild(TvFragment::class.java, false) ?:
                                            (currentFragment as? GameFragment)?.popToChild(GameFragment::class.java, false))))
            return
        }
    }

    override fun onBackPressedSupport() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            pop()
        } else {
            ActivityCompat.finishAfterTransition(this)
        }
    }

    override fun onBackToFirstFragment() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
