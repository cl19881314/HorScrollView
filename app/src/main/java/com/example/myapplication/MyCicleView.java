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
    private int mCentX = 90;
    private int mCentY = 90;
    private Camera camera;
    private Matrix matrix;

    public MyCicleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        camera = new Camera();
        matrix = new Matrix();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        matrix.reset();
//        camera.save();
//        camera.rotateX(60);
//        camera.getMatrix(matrix);
//        camera.restore();
//
//        matrix.preTranslate(-getWidth()/2, -getHeight()/2);
//        matrix.postTranslate(getWidth()/2, getHeight()/2);
//        canvas.rotate(90);
        RectF rel = new RectF(mCentX + 80, mCentY + 80, mCentX + 180, mCentY + 120);
        canvas.drawOval(rel, paint);

        canvas.drawCircle(500f, 500f, 200f,paint);
//        canvas.rotate(80);


    }
}

