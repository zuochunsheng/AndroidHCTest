package com.android.myapplicationtest.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * @author： zcs
 * @time：2019/9/11 on 13:35
 * @description：
 */
public class MyGallery extends Gallery {

    private Camera mCamera = new Camera();
    /** 最大转动角度 */
    private int mMaxRotationAngle = 30;
    /** 最大缩放值 */
    private int mMaxZoom = -300;
    /** 半径值 */
    private int mRadius;

    public MyGallery(Context context){
        super(context);
        this.setStaticTransformationsEnabled(true);
    }

    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setStaticTransformationsEnabled(true);
    }

    public MyGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setStaticTransformationsEnabled(true);
    }

    public int getmMaxRotationAngle() {
        return mMaxRotationAngle;
    }

    public void setmMaxRotationAngle(int mMaxRotationAngle) {
        this.mMaxRotationAngle = mMaxRotationAngle;
    }

    public int getmMaxZoom() {
        return mMaxZoom;
    }

    public void setmMaxZoom(int mMaxZoom) {
        this.mMaxZoom = mMaxZoom;
    }

    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                + getPaddingLeft();
    }

    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    // 重写gallery中的方法，控制gallery中的每个图片的旋转
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        // TODO Auto-generated method stub
        // 取得当前子view的半径值
        int childCenter = getCenterOfView(child);
        // 取得当前子view的宽度
        int childWidth = child.getWidth();
        // 旋转的角度
        int rotationAngle = 0;
        // 重置转换状态
        t.clear();
        // 设置转换类型
        t.setTransformationType(Transformation.TYPE_MATRIX);
        //如果图片位于中心位置不需要进行转换
        if (childCenter == mRadius) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            // 根据图片在gallery中的位置来计算图片的旋转角度
            rotationAngle = (int) (((float) (mRadius - childCenter) / childWidth) * mMaxRotationAngle);
            if (Math.abs(rotationAngle) > mMaxRotationAngle) {
                rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle
                        : mMaxRotationAngle;
            }
            transformImageBitmap((ImageView) child, t, rotationAngle);
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void transformImageBitmap(ImageView child, Transformation t,
                                      int rotationAngle) {
        // 对效果进行保存
        mCamera.save();
        Matrix imageMatrix = t.getMatrix();
        // 图片的高度
        int imageHeight = child.getLayoutParams().height;
        // 图片的宽度
        int imageWidth = child.getLayoutParams().width;
        // 返回旋转角度的绝对值
        int rotation = Math.abs(rotationAngle);

        // 在Z轴上正向移动camera的视角，实际效果为放大图片。
        // 如果在Y轴上移动，则图片上下移动；X轴上对应图片左右移动。
        mCamera.translate(0.0f, 0.0f, 100.0f);
        if (rotation < mMaxRotationAngle) {
            float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
            mCamera.translate(0.0f, 0.0f, zoomAmount);
        }

        // 在Y轴上旋转，对图片纵向向里翻转。
        // 如果在X轴上旋转，则对应图片横向向里翻转。
        mCamera.rotateY(rotationAngle);
        mCamera.getMatrix(imageMatrix);
        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.preTranslate(imageWidth / 2, imageHeight / 2);
        mCamera.restore();
    }

}
