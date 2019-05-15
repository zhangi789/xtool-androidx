package com.ives.cn.util;

import android.text.TextUtils;

/**
 * @author 张海洋
 * @Date on 2019/05/14.
 * @org 上海..科技有限公司
 * @describe   LogUtil
 */
public class LogKit {
    /**
     * 日志开关
     */
    private static boolean isDebug = true;
    private static final String AUTHOR = "------";

    public static void debug(boolean status) {
        isDebug = status;
    }
    public static void d(String tag, String message) {
        if (isDebug) {
            android.util.Log.d(tag, AUTHOR + message);
        }
    }

    public static void i(String tag, String message) {
        if (isDebug) {
            android.util.Log.i(tag, AUTHOR + message);
        }
    }

    /**
     * Json格式化输出
     * @param tag
     * @param message                 内容
     * @param isOutputOriginalContent 是否输入原内容
     */
    public static void json(String tag, String message, boolean isOutputOriginalContent) {
        if (isDebug && !TextUtils.isEmpty(message)) {
            if (isOutputOriginalContent)
                android.util.Log.i(tag, AUTHOR + message);
            android.util.Log.i(tag, AUTHOR + "\n" + BaseKit.format(BaseKit.convertUnicode(message)));
        }
    }
    public static void w(String tag, String message) {
        if (isDebug) {
            android.util.Log.w(tag, AUTHOR + message);
        }

    }

    public static void e(String tag, String message) {
        if (isDebug) {
            android.util.Log.e(tag, AUTHOR + message);
        }
    }


}
