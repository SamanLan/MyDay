package com.samanlan.lib_ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.samanlan.lib_ui.fragment.ShouyeFragment;
import com.samanlan.lib_ui.fragment.TrajectoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        ShouyeFragment shouyeFragment = new ShouyeFragment();
        TrajectoryFragment trajectoryFragment = new TrajectoryFragment();
        TrajectoryFragment meFragment = new TrajectoryFragment();
        list.add(shouyeFragment);
        list.add(trajectoryFragment);
        list.add(meFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
