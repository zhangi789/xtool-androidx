package com.dnxc.cn;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ives.cn.XTool;
import com.ives.cn.permission.AppSettingsDialog;
import com.ives.cn.permission.EasyPermissions;
import com.ives.cn.util.CameraKit;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 张海洋
 * @Date on 2019/05/13.
 * @org 上海..科技有限公司
 * @describe
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {


    private Button mPhoto;
    private Button mGalery;
    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    int flag = 1;
    File mFlie;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mPhoto = (Button) findViewById(R.id.mPhoto);
        mPhoto.setOnClickListener(this);
        mGalery = (Button) findViewById(R.id.mGalery);
        mGalery.setOnClickListener(this);
        mFlie = XTool.getFlie();
        XTool.json("GGG","",false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mGalery:
                boolean b = XTool.checkPermission(this, perms);
                flag = 3;
                if (b == false) {
                    XTool.requestPermission(this, "我需要你！", 80, perms);
                } else {
                    XTool.openGallery(this, 1500);

                }
                break;

            case R.id.mPhoto:
                boolean b2 = XTool.checkPermission(this, perms);
                flag = 2;
                if (b2 == false) {
                    XTool.requestPermission(this, "我需要你！", 80, perms);
                } else {
                    XTool.openCamera(this, 1000, XTool.getPicturePath(mFlie));
                }
                break;
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (flag == 3) {
            XTool.openGallery(this, 1500);
        } else {
            XTool.openCamera(this, 1000, XTool.getPicturePath(mFlie));
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            //当从软件设置界面，返回当前程序时候
            case AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE:
                break;
            case 1000:
                Uri uri = Uri.fromFile(mFlie);
                Log.i("GGG","拍照 "+uri.toString());
                Log.i("GGG","拍照1 "+XTool.getRealFilePathFromUri(this,uri));


                //剩下自己处理   裁剪。。。。。
                break;
            case 1500:
                Uri uri2 = intent.getData();
                Log.i("GGG","图库 "+uri2.toString());
                Log.i("GGG","图库1 "+XTool.getRealFilePathFromUri(this,uri2));
                //剩下自己处理   裁剪。。。。。
                break;
            default:
                break;
        }
    }

}
