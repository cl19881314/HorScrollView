package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class SecondScrollView extends RelativeLayout {
    public SecondScrollView(Context context) {
        this(context, null);
    }

    public SecondScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("chen","------onTouchEvent-------" + ev.getX());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getX() - x > 10 && mIsScroll){
                    mIsScroll = false;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private float x;
    private boolean mIsScroll = true;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("chen","-------------" + ev.getX());
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x = ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (x - ev.getX() > 10 && mIsScroll){
//                    mIsScroll = false;
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        return super.dispatchTouchEvent(ev);
    }
}
