package com.wang.rulerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wang.ruler.RulerView;
import com.wang.ruler.bean.OnBarMoveListener;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private TextView tv_current_time;
    private RulerView ruler_view;
    private long mCurrentTime = 0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ruler_view = findViewById(R.id.ruler_view);
        tv_current_time = findViewById(R.id.tv_current_time);

        initRulerView();
    }

    private void initRulerView() {
        ruler_view.setCurrentTimeMillis(System.currentTimeMillis());
        ruler_view.setOnBarMoveListener(new OnBarMoveListener() {

            @Override
            public void onBarMoving(boolean isLeftDrag, long currentTime) {
                mCurrentTime = currentTime;
                String time = DateUtil.getStringDateByLong(currentTime, DateUtil.DEFAULT_FORMAT);
                tv_current_time.setText(time);
            }

            @Override
            public void onBarMoveFinish(long currentTime) {
                String time = DateUtil.getStringDateByLong(mCurrentTime, DateUtil.DEFAULT_FORMAT);
                Log.i(TAG, "onBarMoveFinish ： " + time);
            }

            @Override
            public void onMoveExceedStartTime() {
                String time = DateUtil.getStringDateByLong(mCurrentTime, DateUtil.DEFAULT_FORMAT);
                Log.i(TAG, "前一天 ： " + time);
            }

            @Override
            public void onMoveExceedEndTime() {
                String time = DateUtil.getStringDateByLong(mCurrentTime, DateUtil.DEFAULT_FORMAT);
                Log.i(TAG, "后一天 ： " + time);
            }

            @Override
            public void isMaxTime(long currentTimeMillis) {
                String time = DateUtil.getStringDateByLong(currentTimeMillis, DateUtil.DEFAULT_FORMAT);
                Log.i(TAG, "到最大时间点了 ： " + time);
            }
        });
    }
}