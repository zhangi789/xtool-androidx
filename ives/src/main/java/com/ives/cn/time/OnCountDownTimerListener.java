package com.ives.cn.time;

/**
 * @author 张海洋
 * @Date on 2019/05/13.
 * @org 上海..科技有限公司
 * @describe 倒计时监听
 */
public interface OnCountDownTimerListener {
    void onTick(long millisUntilFinished);

    void onFinish();
}
