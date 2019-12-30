package com.android.myapplicationtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.myapplicationtest.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author： zcs
 * @time：2019/12/30 on 11:29
 * @description：
 */
public class MyView extends View {

    private static final String TAG = "MyView";
    private int textSize;
    private String textText;
    private int textColor;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        textSize = array.getDimensionPixelSize(R.styleable.MyView_text_size, 15);
        textText = array.getString(R.styleable.MyView_text_text);
        textColor = array.getColor(R.styleable.MyView_text_color,Color.BLACK);

        array.recycle();


    }

//    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private int getMySize(int defaultSize, int measureSpec) {
        // 设定一个默认大小 defaultSize
        int mySize = defaultSize;
        // 获得类型
        int mode = MeasureSpec.getMode(measureSpec);
        // 获得大小
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 分别获得长宽大小
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        // 这里我已圆形控件举例
        // 所以设定长宽相等
        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        // 设置大小
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);

        int r = getMeasuredWidth() /2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        Log.d(TAG, r + "");

        // 圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = r;
        // 圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = r;

        // 定义灰色画笔，绘制圆形
        Paint bacPaint = new Paint();
        bacPaint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY, r, bacPaint);


        // 定义蓝色画笔，绘制文字
        Paint paint = new Paint();
        paint.setColor(textColor);//Color.BLUE
        paint.setTextSize(textSize);//60
        canvas.drawText(textText, 0, r+paint.getTextSize()/2, paint);




    }
}
