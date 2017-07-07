package com.samanlan.lib_ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.samanlan.lib_ui.R;

import java.util.List;

/**
 * Author：zixin on 2017/7/7 16:50
 * E-mail：lanshenming@linghit.com
 */

public class ContentBehavior extends HeaderScrollingViewBehavior {
    public ContentBehavior() {
    }

    public ContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        // 根据月历滑动的距离 / 最大月历滑动距离(周历的高度) * 滑动内容view的高度
        child.setTranslationY((int) (-dependency.getTranslationY() / -getHeaderOffsetRange(parent) * getScrollRange(dependency)));
    }

    @Override
    protected int getScrollRange(View v) {
        if (isDependOn(v)) {
//            return Math.max(0, v.getMeasuredHeight() + getFinalHeight());
            return super.getScrollRange(v);
        } else {
            return super.getScrollRange(v);
        }
    }

    private float getHeaderOffsetRange(ViewGroup viewGroup) {
        // 找到周历获取高度，就是月历可以滑动的最大距离
        return viewGroup.findViewById(R.id.week).getMeasuredHeight();
    }

    private boolean isDependOn(View dependency) {
        // 观察月历view
        return dependency != null && dependency.getId() == R.id.yueli;
    }
}
