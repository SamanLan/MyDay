package com.samanlan.lib_ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.systembar.SystemBarHelper;

import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewContent;
    private PageBottomTabLayout viewBottom;
    private View viewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemBarHelper.immersiveStatusBar(this, 0);
        initView(savedInstanceState);
        SystemBarHelper.setHeightAndPadding(this, viewTop);

    }

    private void initView(Bundle savedInstanceState) {
        viewContent = (ViewPager) findViewById(R.id.view_content);
        viewTop = findViewById(R.id.view_top);
        // 底部栏初始化
        viewBottom = (PageBottomTabLayout) findViewById(R.id.view_bottom);
        NavigationController controller = viewBottom.material()
                .addItem(R.drawable.icon_main, R.drawable.icon_main_check, "首页", 0xFF4D8FFC)
                .addItem(R.drawable.icon_data, R.drawable.icon_data_check, "轨迹", 0xFF4D8FFC)
                .addItem(R.drawable.icon_me, R.drawable.icon_me_check, "我的", 0xFF4D8FFC)
                .setMode(MaterialMode.HIDE_TEXT)
                .build();
    }
}
