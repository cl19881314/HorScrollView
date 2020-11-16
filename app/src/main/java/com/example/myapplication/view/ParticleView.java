package com.example.myapplication.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.Random;

/**
 *
 */
public class ParticleView extends ViewGroup {
    private int NUM_SNOWFLAKES; // 粒子数量
    private int WIDTH, HEIGHT;
    private Context mContext;
    private Random random = new Random(System.currentTimeMillis());
    private int[] starImgList = {R.drawable.ic_add_star_bg_0, R.drawable.ic_add_star_bg_1, R.drawable.ic_add_star_bg_2, R.drawable.ic_add_star_bg_3};

    public ParticleView(Context context) {
        this(context, null);
    }

    public ParticleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarBackground);
        NUM_SNOWFLAKES = typedArray.getInt(R.styleable.StarBackground_starCount, 50);
        typedArray.recycle();
        initView(context);
    }

    public void initView(Context context) {
        WIDTH = ScreenUtils.getScreenWidth(context);
        HEIGHT = ScreenUtils.getScreenHeight(context);
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            ImageView img = new ImageView(context);
            int imgIndex = random.nextInt(starImgList.length);
            img.setImageResource(starImgList[imgIndex]);
            addView(img);
            img.measure(0, 0);
            int width = img.getMeasuredWidth();
            int height = img.getMeasuredHeight();

            int left = random.nextInt(WIDTH);
            img.layout(left, HEIGHT, left + width, HEIGHT + height);

            ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 1f, 0.8f, 0f);
            ObjectAnimator tansY = ObjectAnimator.ofFloat(img, "translationY", 0, -HEIGHT / 3);
            alpha.setRepeatCount(-1);
            tansY.setRepeatCount(-1);
            AnimatorSet set = new AnimatorSet();
            int dur = random.nextInt(5) + 1;
            set.setDuration(dur * 3 * 1000);
            set.playTogether(tansY,alpha);
            set.start();
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
