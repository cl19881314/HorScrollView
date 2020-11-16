package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class HorizontalScrollView extends ViewGroup {
    //手势
    private GestureDetector mGestureDetector;

    private HorizontalScroller mScroller;
    private int curID;
    //快速滑动
    private boolean isFlying;

    //--回调函数-------------------------------------
    private OnChangeListener mListener;

    public void setOnChangeListener(OnChangeListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public interface OnChangeListener {
        void move2dest(int curID);
    }

    public HorizontalScrollView(Context context) {
        this(context, null);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScroller = new HorizontalScroller();
        isFlying = false;
        initGesture(context);
    }

    private int left = 0;
    private int mLastWidth = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 模向移动，
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //给水平方向的每个view定位
//            view.layout(i * getWidth(), 0, getWidth() + i * getWidth(), getHeight());
            int width = view.getMeasuredWidth();
            view.layout(left, 0, left + width, getHeight());
            left = left + width;
//            Log.e("chen", "------------- " + left);
        }
        mLastWidth = getChildAt(getChildCount() - 1).getMeasuredWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int measuredWidth = view.getMeasuredWidth();
//            Log.e("chen","----------"+measuredWidth);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }


//        int childCount = this.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = this.getChildAt(i);
//            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            int cw = child.getMeasuredWidth();
//            // int ch = child.getMeasuredHeight();
//            Log.e("chen", "----222------" + cw);
//        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (!isFlying) {
//                    move2dest();
//                }
//                isFlying = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
        return true;
    }

    public void move2dest() {
        //
        int destID = (getScrollX() + getWidth() / 2) / getWidth();
        move2dest(destID);
    }

    public void move2dest(int destID) {
        curID = destID;

        if (destID > getChildCount() - 1) {
            destID = getChildCount() - 1;
        }

        if (mListener != null) {
            mListener.move2dest(curID);
        }

        int distance = (int) (destID * getWidth() - getScrollX());
        // scrollBy(distance, 0);
        mScroller.startScroll(getScrollX(), getScrollY(), distance, 0);
        invalidate();
    }

    /**
     * invalidate()此方法会触发下面的方法
     */
    @Override
    public void computeScroll() {
        // 如果存在偏移，就不断刷新
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
        super.computeScroll();
    }

    int mScroll = 0;

    private void initGesture(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (mScroll + distanceX >= left - mLastWidth) {
                    scrollTo(left - mLastWidth, 0);
                } else if (mScroll + distanceX <= 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy((int) distanceX, 0);
                    mScroll += distanceX;
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            /**
             * 快速滑动时
             */
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                isFlying = true;
//                if (curID > 0 && velocityX > 0) {// 表示向左移
//                    move2dest(curID - 1);
//                } else if (curID < getChildCount() && velocityX < 0) {
//                    move2dest(curID + 1);// 向右
//                } else {
//                    move2dest();// 移到原位
//                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }
        });
    }
}
