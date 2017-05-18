package com.samanlan.lib_ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/5/19.
 */

public class NoSlideViewPager extends ViewPager {

    private boolean scrollble = false;

    public NoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSlideViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}
