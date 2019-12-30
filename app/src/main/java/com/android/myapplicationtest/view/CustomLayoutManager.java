package com.android.myapplicationtest.view;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author： zcs
 * @time：2019/12/6 on 16:46
 * @description：
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

//    @Override
//    public void onScrollStateChanged(int state) {
//        switch (state) {
//            case RecyclerView.SCROLL_STATE_IDLE:
//                View viewIdle = mPagerSnapHelper.findSnapView(this);
//                int positionIdle = getPosition(viewIdle);
//                if (mOnViewPagerListener != null && getChildCount() == 1) {
//                    mOnViewPagerListener.onPageSelected(positionIdle,positionIdle == getItemCount() - 1);
//                }
//                break;
//            case RecyclerView.SCROLL_STATE_DRAGGING:
//                View viewDrag = mPagerSnapHelper.findSnapView(this);
//                int positionDrag = getPosition(viewDrag);
//                break;
//            case RecyclerView.SCROLL_STATE_SETTLING:
//                View viewSettling = mPagerSnapHelper.findSnapView(this);
//                int positionSettling = getPosition(viewSettling);
//                break;
//        }
//    }
}
