/*
 * Copyright 2017 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ives.cn.sofia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 张海洋
 * @Date on 2019/05/10.
 * @org 上海..科技有限公司
 * @describe  状态栏
 */
public class FitWindowLayout extends ViewGroup {

    public FitWindowLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int contentViewHeight = 0;
        int childCount = getChildCount();
        int menuWidthSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getMode(widthMeasureSpec));
        int menuHeightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getMode(heightMeasureSpec));
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.measure(menuWidthSpec, menuHeightSpec);
            contentViewHeight += view.getMeasuredHeight();
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), contentViewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int allHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int viewHeight = view.getMeasuredHeight();
            view.layout(l, allHeight, r, allHeight + viewHeight);
            allHeight += viewHeight;
        }
    }
}