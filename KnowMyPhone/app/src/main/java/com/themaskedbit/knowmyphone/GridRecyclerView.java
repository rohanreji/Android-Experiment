package com.themaskedbit.knowmyphone;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

public class GridRecyclerView extends RecyclerView {
    public GridRecyclerView(Context context) {
        super(context);
    }
    public GridRecyclerView(Context context, AttributeSet attrs) { super(context, attrs); }

    public GridRecyclerView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count) {
        final LayoutManager layoutManager = getLayoutManager();
        if(getAdapter()!=null && layoutManager instanceof GridLayoutManager){
            GridLayoutAnimationController.AnimationParameters animationParameters = (GridLayoutAnimationController.AnimationParameters)params.layoutAnimationParameters;
            if(animationParameters == null){
                animationParameters = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationParameters;
            }
            animationParameters.count = count;
            animationParameters.index = index;

            final int coloumns =  ((GridLayoutManager)layoutManager).getSpanCount();
            animationParameters.columnsCount = coloumns;
            animationParameters.rowsCount = count/coloumns;

            final int invertedIndex = count - 1 - index;
            animationParameters.column = coloumns - 1 - (invertedIndex%coloumns);
            animationParameters.row = animationParameters.rowsCount - 1 - (invertedIndex/coloumns);
        }
        else {
            super.attachLayoutAnimationParameters(child, params, index, count);
        }

    }
}
