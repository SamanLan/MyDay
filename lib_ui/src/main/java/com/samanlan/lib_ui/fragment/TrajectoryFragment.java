package com.samanlan.lib_ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.samanlan.lib_ui.R;

import java.util.Calendar;

import cn.aigestudio.datepicker.bizs.themes.DPBaseTheme;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import cn.aigestudio.datepicker.views.MonthView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class TrajectoryFragment extends Fragment {

    private DatePicker datePicker;
    private RecyclerView rvContent;
    private TextView tvYear;
    private TextView tvMonth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_trajectory, null, false);
        initView(content);
        return content;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View content) {
        datePicker = (DatePicker) content.findViewById(R.id.date_picker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        datePicker.setDate(year, month);
        datePicker.setMode(DPMode.SINGLE);
        datePicker.setOnDateChangeListener(new MonthView.OnDateChangeListener() {
            @Override
            public void onMonthChange(int month) {
                tvMonth.setText(month + "月");
            }

            @Override
            public void onYearChange(int year) {
                tvYear.setText(year + "");
            }
        });
        datePicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(TrajectoryFragment.this.getActivity(), date, Toast.LENGTH_LONG).show();
            }
        });
        rvContent = (RecyclerView) content.findViewById(R.id.rv_content);
        tvYear = (TextView) content.findViewById(R.id.tv_year);
        tvMonth = (TextView) content.findViewById(R.id.tv_month);
        tvYear.setText(year + "");
        tvMonth.setText(month + "月");
    }

    private void initCalendar() {
        DPBaseTheme baseTheme = new DPBaseTheme() {
            @Override
            public int colorTitleBG() {
                return 0xFF37BAFD;
            }
        };
        DPTManager.getInstance().initCalendar(baseTheme);
    }
}
