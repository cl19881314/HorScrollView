package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        this(context, null);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.e("chen","------onTouchEvent-------" + ev.getX());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                x = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
//                if (ev.getX() - x > 10 && mIsScroll){
//                    mIsScroll = false;
//                    return true;
//                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
