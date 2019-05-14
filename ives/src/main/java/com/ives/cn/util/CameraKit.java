package com.ives.cn.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 * @author 张海洋
 * @Date on 2019/05/13.
 * @org 上海..科技有限公司
 * @describe 打开相机  打开图库    返回Uri
 */
public class CameraKit {
    /**
     * 打开相机
     *
     * @param context
     * @param requestCode
     * @return
     */
    public static void openCamera(Activity context, int requestCode, String picturePath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            /**
             * 指定拍照存储路径
             *
             * 7.0 及其以上使用FileProvider替换'file://'访问
             */
            if (Build.VERSION.SDK_INT >= 24) {
                //这里的BuildConfig，需要是程序包下BuildConfig。
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context.getApplicationContext(),  context.getPackageName()+".android.provider", new File(picturePath)));
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picturePath)));
            }
            context.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 打开图库
     *
     * @param context
     * @param requestCode
     */
    public static void openGallery(Activity context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(intent, requestCode);
    }

}
