package com.samanlan.lib_ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class TrajectoryFragment2 extends Fragment {

    private DatePicker datePicker;
    private RecyclerView rvContent;
    private TextView tvYear;
    private TextView tvMonth;
    private View viewWeek;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_trajectory2, null, false);
        initView(content);
        return content;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View content) {
        datePicker = (DatePicker) content.findViewById(R.id.date_picker);
        viewWeek = content.findViewById(R.id.view_week);
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
                Toast.makeText(TrajectoryFragment2.this.getActivity(), date, Toast.LENGTH_LONG).show();
            }
        });
        rvContent = (RecyclerView) content.findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContent.setAdapter(new RecyclerView.Adapter<Main_recyclerViewHolder>() {
            @Override
            public Main_recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Main_recyclerViewHolder holder;
                if (viewType == 0) {
                    holder = new Main_recyclerViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.layout_recyclerview_main_header, parent, false), viewType);
                } else
                    holder = new Main_recyclerViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.layout_recyclerview_main, parent, false),viewType);

                return holder;
            }

            @Override
            public void onBindViewHolder(Main_recyclerViewHolder holder, int position) {
                if (position != 0) {
                    holder.title.setText("123456");
                } else {
                    holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            System.out.println("touch");
                            datePicker.monthView.dispatchTouchEvent(event);
                            return true;
                        }
                    });
                }
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0) {
                    return 0;
                }
                return 1;
            }
        });
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /*用这种方法，可能在增加view或删除的时候会出现问题，故需要在这些情况手动清零totalDy*/
                totalDy -= dy;
                System.out.println(totalDy+"****"+210 * (-totalDy / 900f));
                if (totalDy > -900 && totalDy <= 0) {
                }
                if (totalDy > -790 && totalDy <= 0) {
                    datePicker.setY(totalDy+210);
                    viewWeek.setY(210 * (-totalDy / 790f));
                }
            }
        });
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

    class Main_recyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public Main_recyclerViewHolder(View view, int viewType) {
            super(view);
            if (viewType != 0) {
                title = (TextView) view.findViewById(R.id.title);
            }
        }
    }
}
