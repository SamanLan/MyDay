package com.samanlan.lib_ui.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：zixin on 2017/5/24 19:00
 * E-mail：lanshenming@linghit.com
 */

public class CalendarBehavior extends CoordinatorLayout.Behavior<View> {
    public CalendarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    int mStartY;

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //记录开始的Y坐标 也就是toolbar起始Y坐标
        if (mStartY == 0) {
            mStartY = -dependency.getHeight();
        }

        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mStartY;

        //改变child的坐标(从消失，到可见)
        child.setY(child.getHeight() * (percent - 1));
        child.setAlpha(percent);
        dependency.setAlpha(1 - percent);
        return true;
    }
}
