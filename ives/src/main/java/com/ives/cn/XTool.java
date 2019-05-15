package com.ives.cn;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Gravity;
import android.widget.Toast;

import com.ives.cn.permission.EasyPermissions;
import com.ives.cn.sofia.Bar;
import com.ives.cn.sofia.Sofia;
import com.ives.cn.time.CountDownTimerSupport;
import com.ives.cn.util.BaseKit;
import com.ives.cn.util.CameraKit;
import com.ives.cn.util.EncryptKit;
import com.ives.cn.util.FileKit;
import com.ives.cn.util.LogKit;
import com.ives.cn.util.SPKit;

import java.io.File;

import javax.xml.validation.Validator;

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


    /**
     * BaseKit
     * <p>
     * App 信息获得
     * 正则表达式  判断是否是手机号
     * Handler
     * dp2px
     */

    public static String getAppVersionName(Context context) {
        return BaseKit.getAppVersionName(context);
    }

    public static int getAppVersionCode(Context context) {
        return BaseKit.getAppVersionCode(context);
    }

    public static boolean isMobileNO(String mobiles) {
        return BaseKit.isMobileNO(mobiles);
    }


    public static int px2dp(Context context, float pxValue) {
        return BaseKit.px2dp(context, pxValue);
    }

    public static int dp2px(Context context, float dpValue) {
        return BaseKit.dp2px(context, dpValue);
    }

    public static boolean isNotEmpty(String result) {
        return BaseKit.isNotEmpty(result);
    }

    /**
     * byte[]   转化为16进制字符串
     *
     * @param bytes
     * @return
     */

    public static String bytes2HexString(byte[] bytes) {
        return BaseKit.bytes2HexString(bytes);
    }

    /**
     * 16进制字符串   转化为byte[]
     *
     * @param hexString
     * @return
     */

    public static byte[] hexString2Bytes(String hexString) {
        return BaseKit.hexString2Bytes(hexString);
    }

    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone4(String mobile_phone) {
        return BaseKit.hideMobilePhone4(mobile_phone);
    }

    /**
     * 获取当前时间，格式为：yyyy-MM-dd HH:mm:ss   默认
     *
     * @return String
     */
    public static String getSysCurTime(String format) {

        return BaseKit.getSysCurTime(format);
    }


    /**
     * Handler
     *
     * @param mHandler
     * @param what
     * @param data
     */
    public static void handleMsg(Handler mHandler, int what, Bundle data) {
        BaseKit.handleMsg(mHandler, what, data);
    }

    public static void handleDelayedMsg(Handler mHandler, int what, long time, Bundle data) {
        BaseKit.handleDelayedMsg(mHandler, what, time, data);
    }


    /**
     * LogKit
     * 然后使用跟Log 一致
     */
    public static void json(String tag, String message, boolean isOutputOriginalContent) {
        LogKit.json(tag, message, isOutputOriginalContent);
    }

    public static void d(String tag, String message) {
        LogKit.d(tag, message);
    }

    public static void i(String tag, String message) {
        LogKit.i(tag, message);

    }

    public static void w(String tag, String message) {
        LogKit.w(tag, message);

    }


    /**
     * SPKit
     * SharedPreferences 使用一致
     */


    public static void putString(Context context, String key, String value) {
        SPKit.putString(context, key, value);
    }

    public static String getString(Context context, String key, String defValue) {
        return SPKit.getString(context, key, defValue);
    }


    public static void putInt(Context context, String key, int value) {
        SPKit.putInt(context, key, value);
    }

    public static int getInt(Context context, String key, int defValue) {
        return SPKit.getInt(context, key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SPKit.putBoolean(context, key, value);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return SPKit.getBoolean(context, key, defValue);
    }


    public static void putFloat(Context context, String key, float value) {
        SPKit.putFloat(context, key, value);
    }

    public static float getFloat(Context context, String key, float defValue) {
        return SPKit.getFloat(context, key, defValue);
    }


    /**
     * Toast
     */
    /**
     * 居中显示的Toast
     *
     * @param content
     */
    public static void showLongToast(Context context, String content) {
        BaseKit.showLongToast(context, content);
    }

    /**
     * 居中显示的Toast
     *
     * @param content
     */
    public static void showShortToast(Context context, String content) {
        BaseKit.showShortToast(context, content);
    }


    /**
     *    Base64    MD5
     *
     */


    /**
     * Base64编码
     *
     * @param input 要编码的字符串
     * @return Base64编码后的字符串
     */
    public static byte[] base64Encode(String input) {
        return EncryptKit.base64Encode(input.getBytes());
    }

    /**
     * Base64编码
     *
     * @param input 要编码的字节数组
     * @return Base64编码后的字符串
     */
    public static byte[] base64Encode(byte[] input) {
        return EncryptKit.base64Encode(input);

    }

    /**
     * Base64编码
     *
     * @param input 要编码的字节数组
     * @return Base64编码后的字符串
     */
    public static String base64Encode2String(byte[] input) {


        return EncryptKit.base64Encode2String(input);

    }

    /**
     * Base64解码
     *
     * @param input 要解码的字符串
     * @return Base64解码后的字符串
     */
    public static byte[] base64Decode(String input) {

        return EncryptKit.base64Decode(input);
    }

    /**
     * Base64解码
     *
     * @param input 要解码的字符串
     * @return Base64解码后的字符串
     */
    public static byte[] base64Decode(byte[] input) {
        return EncryptKit.base64Decode(input);
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String getMD5(String data) {

        return EncryptKit.getMD5(data);

    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String getMD5(byte[] data) {
        return EncryptKit.getMD5(data);
    }


}
