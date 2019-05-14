package com.ives.cn;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.ives.cn.permission.EasyPermissions;
import com.ives.cn.sofia.Bar;
import com.ives.cn.sofia.Sofia;
import com.ives.cn.time.CountDownTimerSupport;
import com.ives.cn.util.CameraKit;
import com.ives.cn.util.FileKit;

import java.io.File;

/**
 * @author 张海洋
 * @Date on 2019/05/10.
 * @org 上海..科技有限公司
 * @describe
 */
public class XTool {
    /**
     * 状态栏使用 StatusBar
     */
    public static Bar getStatusBar(Activity activity) {
        return Sofia.with(activity);
    }

    /**
     * @param millisInFuture    倒计时时间
     * @param countDownInterval 间隔时间
     * @return
     */
    public static CountDownTimerSupport getCountDown(long millisInFuture, long countDownInterval) {
        return new CountDownTimerSupport(millisInFuture, countDownInterval);
    }

    /**
     * @param context return true:已经获取权限
     *                return false: 未获取权限，主动请求权限
     */
    // @AfterPermissionGranted 是可选的
    public static boolean checkPermission(Activity context, String[] perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    /**
     * @param context
     * @param tip         当用户禁止之后提示语
     * @param requestCode
     * @param perms       String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
     */
    public static void requestPermission(Activity context, String tip, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(context, tip, requestCode, perms);
    }


    /**
     * 打开相机
     *
     * @param context
     * @param requestCode
     * @return
     */
    public static void openCamera(Activity context, int requestCode, String picturePath) {
        CameraKit.openCamera(context, requestCode, picturePath);
    }

    /**
     * 打开图库
     *
     * @param context
     * @param requestCode
     */
    public static void openGallery(Activity context, int requestCode) {
        CameraKit.openGallery(context, requestCode);
    }

    /**
     * 获得存储的文件
     * getExternalFilesDir()提供的是私有的目录，在app卸载后会被删除
     *
     * @param context
     * @param
     * @return
     */
    public static String getDiskFileName(Context context) {
        return FileKit.getDiskFileName(context);
    }

    /**
     * 获得磁盘存储File
     * 好处:提供的是私有的目录，在app卸载后会被删除
     *
     * @param context
     * @return
     */
    public static File getDiskFile(Context context) {
        return FileKit.getFile(context);
    }

    /**
     * 自定义获得文件夹存储文件
     * 路径：/storage/emulated/0/image/ab777ea0de482551002c45a3548edd86.png
     * 文件名：ab777ea0de482551002c45a3548edd86.png     当前时间+MD5 加密方式
     *
     * @param
     * @return
     */
    public static File getFlie() {
        return FileKit.getFlie();
    }

    /**
     * 根据存储的file String picturePath
     * 根据Flie 获得 picturePath
     *
     * @param mFile
     * @return
     */
    public static String getPicturePath(File mFile) {
        return FileKit.getPicturePath(mFile);
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(Context context, Uri uri) {
        return FileKit.uriConvertPath(context, uri);
    }
}
