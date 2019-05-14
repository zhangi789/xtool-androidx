package com.dnxc.cn;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ives.cn.XTool;
import com.ives.cn.time.CountDownTimerSupport;
import com.ives.cn.time.OnCountDownTimerListener;
import com.ives.cn.time.TimerState;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 张海洋
 * @Date on 2019/05/13.
 * @org 上海..科技有限公司
 * @describe
 */
public class CutDownTimeActivity extends AppCompatActivity {

    TextView tv;
    TextView tv_state;
    EditText ed_future;
    EditText ed_interval;

    private CountDownTimerSupport mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutdown_time);
        tv = (TextView) findViewById(R.id.tv);
        tv_state = (TextView) findViewById(R.id.tv_state);
        ed_future = (EditText) findViewById(R.id.ed_future);
        ed_interval = (EditText) findViewById(R.id.ed_interval);
    }


    public void clickStart(View v) {
        if (mTimer == null) {
            long millisInFuture = Long.parseLong(ed_future.getText().toString());
            long countDownInterval = Long.parseLong(ed_interval.getText().toString());
            mTimer = XTool.getCountDown(millisInFuture, countDownInterval);
            mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
                @Override
                public void onTick(long millisUntilFinished) {
                    tv.setText(millisUntilFinished + "ms\n" + millisUntilFinished / 1000 + "s");
                    Log.d("CountDownTimerSupport", "onTick : " + millisUntilFinished + "ms");
                }

                @Override
                public void onFinish() {
                    tv.setText("已停止");
                    Log.d("CountDownTimerSupport", "onFinish");
                }
            });
        }
        mTimer.start();
        tv_state.setText(getStateText());
    }

    public void clickPause(View v) {
        if (mTimer != null) {
            mTimer.pause();

            tv_state.setText(getStateText());
        }
    }

    public void clickResume(View v) {
        if (mTimer != null) {
            mTimer.resume();

            tv_state.setText(getStateText());
        }
    }

    public void clickCancel(View v) {
        if (mTimer != null) {
            mTimer.stop();

            tv_state.setText(getStateText());
        }
    }

    public void clickResetStart(View v) {
        if (mTimer != null) {
            mTimer.reset();
            mTimer.start();

            tv_state.setText(getStateText());
        }
    }

    public void clickList(View v) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTimer != null) {
            mTimer.resume();
            tv_state.setText(getStateText());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.pause();
            tv_state.setText(getStateText());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.stop();
            tv_state.setText(getStateText());
        }
    }

    private String getStateText() {
        TimerState state = mTimer.getTimerState();
        if (TimerState.START == state) {
            return "正在倒计时";
        } else if (TimerState.PAUSE == state) {
            return "倒计时暂停";
        } else if (TimerState.FINISH == state) {
            return "倒计时闲置";
        }
        return "";
    }
}
