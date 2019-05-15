package com.dnxc.cn;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.ives.cn.XTool;
import com.ives.cn.time.CountDownTimerSupport;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CountDownTimerSupport countDown;

    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Button mStatus;
    private Button mPermission;
    private Button mTakePhot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        //    XTool.getStatusBar(this).statusBarDarkFont().statusBarBackground(Color.WHITE);
     /* countDown = XTool.getCountDown(Long.parseLong("20000"), Long.parseLong("1000"));
        countDown.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {

            }
        });
        countDown.start();
        countDown.reset();*/


    }

    private void initviews() {
        mStatus = (Button) findViewById(R.id.mStatus);
        mStatus.setOnClickListener(this);
        mPermission = (Button) findViewById(R.id.mPermission);
        mPermission.setOnClickListener(this);
        mTakePhot = (Button) findViewById(R.id.mTakePhoto);
        mTakePhot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mStatus:
                startActivity(new Intent(this, CutDownTimeActivity.class));
                break;
            case R.id.mPermission:
                startActivity(new Intent(this, PermissionActivity.class));
                break;
            case R.id.mTakePhoto:
                startActivity(new Intent(this, CameraActivity.class));
                break;
        }
    }
}
