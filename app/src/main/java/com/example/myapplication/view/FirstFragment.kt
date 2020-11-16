package com.example.myapplication.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_first_layout.*


class FirstFragment : Fragment() {
    private var mIsStart = false
    private var mTranslation: Animator? = null
    private var mScrollState = -1
    private var mMyIsSelect = false
    private var mSelectPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_first_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private var mIndex = 0f
    fun startLeftAnimotor(position: Int, dis: Int) {
        if (mSelectPosition != position){
            return
        }
        if (mScrollState == -1 || mScrollState == 0 || mScrollState == 2) {
            return
        }
        if (mIndex > 200) {
            return
        }
        Log.e("chen","-----mScrollState-----  $mScrollState")
        val btree = TranslateAnimation(
            mIndex, mIndex + 1.2f, 0f, 0f
        )
        btree.fillAfter = true
        btree.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                mIsStart = false
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        imgYest.startAnimation(btree)
        mIndex += 1.2f
    }

    fun selectAnimotor(position: Int) {
        mSelectPosition = position
        var tranYun = ObjectAnimator.ofFloat(imgYun, "translationX", 0f, 10f, -10f)
        tranYun!!.duration = 1 * 1000.toLong()
        tranYun.interpolator = BounceInterpolator()
        tranYun!!.start()

        var imgYest = ObjectAnimator.ofFloat(imgYest, "translationX", 0f, -10f, 10f)
        imgYest.duration = 1 * 1000.toLong()
        imgYest.interpolator = BounceInterpolator()
        imgYest.start()
        mMyIsSelect = true
    }

    fun scrollStateChanged(state: Int) {
        mScrollState = state
        if (state == 2) {
            val btree = TranslateAnimation(
                mIndex, 0f, 0f, 0f
            )
            btree.fillAfter = true
            btree.duration = 1 * 1000
            btree.interpolator = BounceInterpolator()
            btree.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            imgYest.startAnimation(btree)
            mIndex = 0f
        }
    }

    fun startRightAnimotor(position: Int, dis: Int) {
        if (mSelectPosition != position){
            return
        }
        if (mScrollState == 0 || mScrollState == 2) {
            return
        }
        if (mIndex > 200) {
            return
        }
        val btree = TranslateAnimation(
            mIndex, mIndex - 1.2f, 0f, 0f
        )
        btree.fillAfter = true
        btree.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                mIsStart = false
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        imgYest.startAnimation(btree)
        mIndex -= 1.2f
    }
}