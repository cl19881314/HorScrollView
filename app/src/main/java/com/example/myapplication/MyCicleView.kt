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
    private lateinit var mSmallStarPaint: Paint
    private lateinit var mSmallStarPaint2: Paint
    private lateinit var mSmallStarPaint3: Paint
    private lateinit var mJianPaint: Paint
    private var mCentX = 480f
    private var mCentY = 800f
    private var mRotateCentX = 480f
    private var mRotateCentY = 800f
    private val mLeft = 400f
    private val mTop = 80f
    private val mTimeSleep = 15
    private var mRunDegree = 0f
    private var mRunDegree2 = 90f
    private var mRunDegree3 = 180f
    private var mOverX = 0f
    private var mOverY = 0f
    private var mOverX2 = 0f
    private var mOverY2 = 0f
    private var mOverX3 = 0f
    private var mOverY3 = 0f
    private var mSmallRadius = 20f
    private var mCanDraw = true

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
        //去锯齿
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        //范围为0~255
        paint.alpha = 100
        paint.strokeWidth = 3f
        mSmallStarPaint = Paint()
        mSmallStarPaint.isAntiAlias = true
        mSmallStarPaint.color = Color.WHITE
        mSmallStarPaint.style = Paint.Style.FILL
        mSmallStarPaint.alpha = 100

        mSmallStarPaint2 = Paint()
        mSmallStarPaint2.isAntiAlias = true
        mSmallStarPaint2.color = Color.WHITE
        mSmallStarPaint2.style = Paint.Style.FILL
        mSmallStarPaint2.alpha = 100

        mSmallStarPaint3 = Paint()
        mSmallStarPaint3.isAntiAlias = true
        mSmallStarPaint3.color = Color.WHITE
        mSmallStarPaint3.style = Paint.Style.FILL

        mJianPaint = Paint()
        mJianPaint.isAntiAlias = true
        mJianPaint.color = Color.WHITE
        mJianPaint.style = Paint.Style.STROKE
        mJianPaint.strokeWidth = 3f
        mJianPaint.alpha = 90
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_now_new);
        canvas.drawBitmap(
            bitmap,
            mRotateCentX - bitmap.width / 2f,
            mRotateCentY - bitmap.height / 2,
            paint
        )
        val rel = RectF(mCentX - mLeft, mCentY - mTop, mCentX + mLeft, mCentY + mTop)
        canvas.drawCircle(mOverX, mOverY, mSmallRadius, mSmallStarPaint)

        mJianPaint.alpha = 90
//        paint.alpha = 100
        canvas.drawArc(rel, 30f, 120f, false, paint)
        drawLeftRight(canvas, rel)
        canvas.save()

        canvas.rotate(45f, mRotateCentX, mRotateCentY)
        canvas.drawCircle(mOverX2, mOverY2, mSmallRadius, mSmallStarPaint2)
        mJianPaint.alpha = 90
        canvas.drawArc(rel, 30f, 120f, false, paint)
        drawLeftRight(canvas, rel)
        canvas.save()

        canvas.rotate(-90f, mRotateCentX, mRotateCentY)
        mJianPaint.alpha = 90
        canvas.drawArc(rel, 30f, 120f, false, paint)

        canvas.drawCircle(mOverX3, mOverY3, mSmallRadius, mSmallStarPaint3)
        drawLeftRight(canvas, rel)
    }

    private fun drawLeftRight(canvas: Canvas, rel: RectF) {
        //画右侧侧透明圆弧
        mJianPaint.alpha = 80
        canvas.drawArc(rel, 20f, 10f, false, mJianPaint)
        mJianPaint.alpha = 70
        canvas.drawArc(rel, 10f, 10f, false, mJianPaint)
        mJianPaint.alpha = 60
        canvas.drawArc(rel, 0f, 10f, false, mJianPaint)
        mJianPaint.alpha = 50
        canvas.drawArc(rel, -10f, 10f, false, mJianPaint)
        mJianPaint.alpha = 40
        canvas.drawArc(rel, -20f, 10f, false, mJianPaint)
        mJianPaint.alpha = 30
        canvas.drawArc(rel, -30f, 10f, false, mJianPaint)
        mJianPaint.alpha = 20
        canvas.drawArc(rel, -40f, 10f, false, mJianPaint)
        mJianPaint.alpha = 10
        canvas.drawArc(rel, -50f, 10f, false, mJianPaint)

        //画左侧透明圆弧
        mJianPaint.alpha = 80
        canvas.drawArc(rel, 150f, 10f, false, mJianPaint)
        mJianPaint.alpha = 70
        canvas.drawArc(rel, 160f, 10f, false, mJianPaint)
        mJianPaint.alpha = 60
        canvas.drawArc(rel, 170f, 10f, false, mJianPaint)
        mJianPaint.alpha = 50
        canvas.drawArc(rel, 180f, 10f, false, mJianPaint)
        mJianPaint.alpha = 40
        canvas.drawArc(rel, 190f, 10f, false, mJianPaint)
        mJianPaint.alpha = 30
        canvas.drawArc(rel, 200f, 10f, false, mJianPaint)
        mJianPaint.alpha = 20
        canvas.drawArc(rel, 210f, 10f, false, mJianPaint)
        mJianPaint.alpha = 10
        canvas.drawArc(rel, 220f, 10f, false, mJianPaint)
    }

    private fun calculatPoint() {
        calculatCenterPoint1()
        calculatCenterPoint2()
        calculatCenterPoint3()
        invalidate()
        mHandler.sendEmptyMessageDelayed(1, mTimeSleep.toLong())
    }

    private fun calculatCenterPoint1() {
        if (mRunDegree >= 360) {
            mRunDegree = 0f
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
        } else if (mRunDegree == 90f) {
            mOverX = 0F
            mOverY = b.toFloat()
        } else if (mRunDegree == 270f) {
            mOverX = 0F
            mOverY = -b.toFloat()
        }
        mOverX += mCentX
        mOverY += mCentY
        mRunDegree += when (mRunDegree) {
            in 70..120 -> 3f
            in 120..150 -> 2f
            in 30..70 -> 2f
            in 240..310 -> 3f
            in 210..230 -> 3f
            in 310..330 -> 2f
            else -> 0.5f

        }
        if (mRunDegree in 0..90) {
            mSmallStarPaint.alpha = 127 + 255 / 180 * mRunDegree.toInt()
        } else if (mRunDegree in 90..180) {
            mSmallStarPaint.alpha = 127 + 255 / 180 * (180 - mRunDegree).toInt()
        } else if (mRunDegree in 180..190) {
            mSmallStarPaint.alpha = 127 - 255 / 180 * (mRunDegree - 180).toInt() * 2
        } else if (mRunDegree in 350..360) {
            mSmallStarPaint.alpha = 127 - 255 / 180 * (360 - mRunDegree).toInt() * 2
        } else {
            mSmallStarPaint.alpha = 0
        }
    }

    private fun calculatCenterPoint2() {
        if (mRunDegree2 >= 360) {
            mRunDegree2 = 0f
        }
        val a = mLeft.toDouble()
        val b = mTop.toDouble()
        val sqrt =
            Math.sqrt(b * b + a * a * Math.pow(Math.tan(mRunDegree2 * Math.PI / 180), 2.toDouble()))
        mOverX2 = (a * b / sqrt).toFloat()
        mOverY2 = (a * b * Math.tan(mRunDegree2 * Math.PI / 180) / sqrt).toFloat()
        if (mRunDegree2 in 0 until 90 || mRunDegree2 in 271..360) {
        } else if (mRunDegree2 in 91 until 270) {
            mOverX2 = -mOverX2
            mOverY2 = -mOverY2
        } else if (mRunDegree2 == 90f) {
            mOverX2 = 0F
            mOverY2 = b.toFloat()
        } else if (mRunDegree2 == 270f) {
            mOverX2 = 0F
            mOverY2 = -b.toFloat()
        }
        mOverX2 += mCentX
        mOverY2 += mCentY
        mRunDegree2 += when (mRunDegree2) {
            in 70..120 -> 3f
            in 120..150 -> 2f
            in 30..70 -> 2f
            in 240..310 -> 3f
            in 210..230 -> 3f
            in 310..330 -> 2f
            else -> 0.5f

        }
        if (mRunDegree2 in 0..90) {
            mSmallStarPaint2.alpha = 127 + 255 / 180 * mRunDegree2.toInt()
        } else if (mRunDegree2 in 90..180) {
            mSmallStarPaint2.alpha = 127 + 255 / 180 * (180 - mRunDegree2).toInt()
        } else if (mRunDegree2 in 180..190) {
            mSmallStarPaint2.alpha = 127 - 255 / 180 * (mRunDegree2 - 180).toInt() * 2
        } else if (mRunDegree2 in 350..360) {
            mSmallStarPaint2.alpha = 127 - 255 / 180 * (360 - mRunDegree2).toInt() * 2
        } else {
            mSmallStarPaint2.alpha = 0
        }
    }

    private fun calculatCenterPoint3() {
        if (mRunDegree3 >= 360) {
            mRunDegree3 = 0f
        }
        val a = mLeft.toDouble()
        val b = mTop.toDouble()
        val sqrt =
            Math.sqrt(b * b + a * a * Math.pow(Math.tan(mRunDegree3 * Math.PI / 180), 2.toDouble()))
        mOverX3 = (a * b / sqrt).toFloat()
        mOverY3 = (a * b * Math.tan(mRunDegree3 * Math.PI / 180) / sqrt).toFloat()
        if (mRunDegree3 in 0 until 90 || mRunDegree3 in 271..360) {
        } else if (mRunDegree3 in 91 until 270) {
            mOverX3 = -mOverX3
            mOverY3 = -mOverY3
        } else if (mRunDegree3 == 90f) {
            mOverX3 = 0F
            mOverY3 = b.toFloat()
        } else if (mRunDegree3 == 270f) {
            mOverX3 = 0F
            mOverY3 = -b.toFloat()
        }
        mOverX3 += mCentX
        mOverY3 += mCentY
        mRunDegree3 += when (mRunDegree3) {
            in 70..120 -> 3f
            in 120..150 -> 2f
            in 30..70 -> 2f
            in 240..310 -> 3f
            in 210..230 -> 3f
            in 310..330 -> 2f
            else -> 0.5f
        }
        if (mRunDegree3 in 0..90) {
            mSmallStarPaint3.alpha = 127 + 255 / 180 * mRunDegree3.toInt()
        } else if (mRunDegree3 in 90..180) {
            mSmallStarPaint3.alpha = 127 + 255 / 180 * (180 - mRunDegree3).toInt()
        } else if (mRunDegree3 in 180..190) {
            mSmallStarPaint3.alpha = 127 - 255 / 180 * (mRunDegree3 - 180).toInt() * 2
        } else if (mRunDegree3 in 350..360) {
            mSmallStarPaint3.alpha = 127 - 255 / 180 * (360 - mRunDegree3).toInt() * 2
        } else {
            mSmallStarPaint3.alpha = 0
        }
    }

    private val mHandler: Handler = object : Handler(
        Looper.getMainLooper()
    ) {
        override fun dispatchMessage(msg: Message) {
            super.dispatchMessage(msg)
            if (msg.what == 1) {
                if (!mCanDraw) {
                    return
                }
                calculatPoint()
            }
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            mCentX = width / 2f
            mCentY = height / 2f

            mRotateCentX = mCentX
            mRotateCentY = mCentY + mTop
            calculatPoint()
        }
    }

    fun clear() {
        mCanDraw = false
        mHandler.removeCallbacksAndMessages(null)
    }
}