package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.view.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity : AppCompatActivity() {
    private lateinit var mFirstFragment: FirstFragment
    private lateinit var mFirstFragment2: FirstFragment
    private lateinit var mFirstFragment3: FirstFragment
    private var mDis = 0
    private var mSelectIndex = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        viewPager.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollX - mDis > 0) {
                Log.e("chen", "--setOnScrollChangeListener-- $mSelectIndex ")
                when (mSelectIndex) {
                    0 -> {
                        mFirstFragment.startLeftAnimotor(mSelectIndex, scrollX)
                    }
                    1 -> {
                        mFirstFragment2.startLeftAnimotor(mSelectIndex, scrollX)
                    }
                    2 -> {
                        mFirstFragment3.startLeftAnimotor(mSelectIndex, scrollX)
                    }
                }
            } else {
                when (mSelectIndex) {
                    0 -> {
                        mFirstFragment.startRightAnimotor(mSelectIndex, scrollX)
                    }
                    1 -> {
                        mFirstFragment2.startRightAnimotor(mSelectIndex, scrollX)
                    }
                    2 -> {
                        mFirstFragment3.startRightAnimotor(mSelectIndex, scrollX)
                    }
                }
            }
            mDis = scrollX

        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.e("chen", "--onPageScrollStateChanged-- $mSelectIndex ---  $state")
                when (mSelectIndex) {
                    0 -> {
                        mFirstFragment.scrollStateChanged(state)
                    }
                    1 -> {
                        mFirstFragment2.scrollStateChanged(state)
                    }
                    2 -> {
                        mFirstFragment3.scrollStateChanged(state)
                    }
                }


            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
//                Log.e("chen", "-----  $position----  $positionOffset----  $positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
                mSelectIndex = position
                Log.e("chen", "--onPageSelected-- $mSelectIndex ")
                when (mSelectIndex) {
                    0 -> {
                        mFirstFragment.selectAnimotor(position)
                    }
                    1 -> {
                        mFirstFragment2.selectAnimotor(position)
                    }
                    2 -> {
                        mFirstFragment3.selectAnimotor(position)
                    }
                }
            }

        })

        var list = ArrayList<Fragment>()
        mFirstFragment = FirstFragment()
        mFirstFragment2 = FirstFragment()
        mFirstFragment3 = FirstFragment()
        list.add(mFirstFragment)
        list.add(mFirstFragment2)
        list.add(mFirstFragment3)
        var adapter = ViewPagerAdapter(supportFragmentManager, list)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3
    }
}
