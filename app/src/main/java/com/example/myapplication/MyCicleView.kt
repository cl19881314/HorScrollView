package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.View


class MyCicleView : View {
    private lateinit var paint: Paint
    private lateinit var alPpaint: Paint
    private lateinit var mPath: Path
    private val mCentX = 480f
    private val mCentY = 800f
    private val mLeft = 480f
    private val mTop = 100f
    private val mTimeSleep = 15
    private var mRunDegreeAdd = 2
    private var mRunDegree = 0
    private var mOverX = 0f
    private var mOverY = 0f
    private var isFirst = true

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        inivView()
    }

    private fun inivView() {
        mPath = Path()
        //去锯齿
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        alPpaint = Paint()
        alPpaint.isAntiAlias = true
        alPpaint.color = Color.WHITE
        alPpaint.style = Paint.Style.FILL
        alPpaint.alpha = 30
        calculatCenterPoint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_now_new);
        canvas.drawBitmap(
            bitmap,
            mCentX - bitmap.width / 2f,
            mCentY + mTop - bitmap.height / 2,
            paint
        )
        val rel = RectF(mCentX - mLeft, mCentY - mTop, mCentX + mLeft, mCentY + mTop)
        canvas.drawOval(rel, paint)
        canvas.drawCircle(mCentX, mCentY, 5f, paint)
        canvas.drawCircle(mOverX, mOverY, 10f, paint)
//        paint.color = Color.BLACK //设置画笔颜色
//        canvas.drawLine(mOverX, mOverY, mOverX + 10, mOverY + 10, paint)

//        paint.color = Color.BLACK //设置画笔颜色
//        canvas.drawColor(Color.WHITE) //设置背景颜色
//        paint.strokeWidth = 1.0.toFloat() //设置线宽
//        canvas.drawLine(50f, 50f, 450f, 50f, paint) //绘制直线
//        paint.strokeWidth = 5.0.toFloat() //设置线宽
//        canvas.drawLine(50f, 150f, 450f, 150f, paint) //绘制直线
//        paint.strokeWidth = 10.0.toFloat() //设置线宽
//        canvas.drawLine(50f, 250f, 450f, 250f, paint) //绘制直线
//        paint.strokeWidth = 15.0.toFloat() //设置线宽
//
//        canvas.drawLine(50f, 350f, 450f, 350f, paint) //绘制直线
//
//        paint.strokeWidth = 20.0.toFloat() //设置线宽
//
//        canvas.drawLine(50f, 450f, 450f, 450f, paint) //绘制直线

//        canvas.save()
//        canvas.rotate(45f, mCentX, mCentY)
//        val rel2 = RectF(mCentX - mLeft, mCentY - mTop, mCentX + mLeft, mCentY + mTop)
//        canvas.drawOval(rel2, paint)
//        canvas.drawCircle(mCentX, mCentY, 5f, paint)
//        canvas.save()
//        canvas.rotate(90f, mCentX, mCentY)
//        val rel3 = RectF(mCentX - mLeft, mCentY - mTop, mCentX + mLeft, mCentY + mTop)
//        canvas.drawOval(rel3, paint)
//        canvas.drawCircle(mCentX, mCentY, 5f, paint)

    }

    private fun calculatCenterPoint() {
        if (mRunDegree >= 360) {
            mRunDegree = 0
        }
        val a = mLeft.toDouble()
        val b = mTop.toDouble()
        val sqrt =
            Math.sqrt(b * b + a * a * Math.pow(Math.tan(mRunDegree * Math.PI / 180), 2.toDouble()))
        mOverX = (a * b / sqrt).toFloat()
        mOverY = (a * b * Math.tan(mRunDegree * Math.PI / 180) / sqrt).toFloat()
        if (mRunDegree in 0 until 90 || mRunDegree in 271..360) {
//            mOverX = (a * b / sqrt).toFloat()
//            mOverY = (a * b * Math.tan(mRunDegree * Math.PI / 180) / sqrt).toFloat()
        } else if (mRunDegree in 91 until 270) {
            mOverX = -mOverX
            mOverY = -mOverY
        } else if (mRunDegree == 90) {
            mOverX = 0F
            mOverY = b.toFloat()
        } else if (mRunDegree == 270) {
            mOverX = 0F
            mOverY = -b.toFloat()
        }
        mOverX += mCentX
        mOverY += mCentY
        mRunDegree += mRunDegreeAdd

        invalidate()
//        postInvalidate()
        mHandler.sendEmptyMessageDelayed(1, mTimeSleep.toLong())
    }

    private val mHandler: Handler = object : Handler(
        Looper.getMainLooper()
    ) {
        override fun dispatchMessage(msg: Message) {
            super.dispatchMessage(msg)
            if (msg.what == 1) {
                calculatCenterPoint()
            }
        }
    }
}