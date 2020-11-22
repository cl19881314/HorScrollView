package com.example.myapplication;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class MyCicleView extends View {
    private Paint paint = new Paint();
    private float mCentX = 480f;
    private float mCentY = 800f;
    private Camera mCamera;
    private Matrix mMatrix;

    public MyCicleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCamera = new Camera();
        mMatrix = new Matrix();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rel = new RectF(mCentX - 480, mCentY - 180, mCentX + 480, mCentY + 180);
        canvas.drawOval(rel, paint);
        canvas.drawCircle(mCentX, mCentY, 5f, paint);
        canvas.save();

        canvas.rotate(45, mCentX, mCentY);

        RectF rel2 = new RectF(mCentX - 480, mCentY - 130, mCentX + 480, mCentY + 130);
        canvas.drawOval(rel2, paint);
        canvas.drawCircle(mCentX, mCentY, 5f, paint);
        canvas.save();

        canvas.rotate(90, mCentX, mCentY);
        RectF rel3 = new RectF(mCentX - 480, mCentY - 130, mCentX + 480, mCentY + 130);
        canvas.drawOval(rel3, paint);
        canvas.drawCircle(mCentX, mCentY, 5f, paint);
    }
}

