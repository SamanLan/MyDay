package com.samanlan.lib_ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cn.aigestudio.datepicker.views.DatePicker;

/**
 * Author：zixin on 2017/5/25 11:00
 * E-mail：lanshenming@linghit.com
 */

public class CalendarLayout extends ViewGroup {

    View weekView;
    DatePicker datePicker;
    RecyclerView recyclerView;

    public CalendarLayout(Context context) {
        super(context);
    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        datePicker = (DatePicker) getChildAt(0);
        recyclerView = (RecyclerView) getChildAt(1);
        weekView = getChildAt(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (i == 2) {
                view.layout(left, -view.getMeasuredHeight(), right, 0);
            } else {
                view.layout(left, 0, right, view.getMeasuredHeight());
            }
        }
    }
}
