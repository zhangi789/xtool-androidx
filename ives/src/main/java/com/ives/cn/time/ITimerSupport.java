package com.ives.cn.time;

/**
 * @author 张海洋
 * @Date on 2019/05/13.
 * @org 上海..科技有限公司
 * @describe
 */
public interface ITimerSupport {
    void start();

    void pause();

    void resume();

    void stop();

    void reset();
}
