package com.kingja.loadsir;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.core.TargetContext;

/**
 * Description:TODO
 * Create Time:2017/9/4 16:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirUtil {
    public static TargetContext getTargetContext(Object target) {
        ViewGroup contentParent;
        Context context;
        if (target instanceof Activity) {
            //如果target是Activity旧获取Activity的父类容器
            Activity activity = (Activity) target;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (target instanceof View) {
            //如果target是View，就获取该View的父类容器
            View view = (View) target;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("The target must be within Activity, Fragment, View.");
        }
        int childIndex = 0;
        //获取contentParent 中的子View的数量
        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        View oldContent;
        //如果target是View
        if (target instanceof View) {
            //将target改为View
            oldContent = (View) target;
            for (int i = 0; i < childCount; i++) {
                //遍历容器中的子View，如果与传入的target所传入的View相同
                if (contentParent.getChildAt(i) == oldContent) {
                    //父容器中的子View 的ID将确认下来，记录之。
                    childIndex = i;
                    break;
                }
            }
        } else {
            //如果target是activity,那oldContent就是Actiivty的顶级容器
            oldContent = contentParent != null ? contentParent.getChildAt(0) : null;
        }
        if (oldContent == null) {
            // oldContent == null 报错
            throw new IllegalArgumentException(String.format("enexpected error when register LoadSir in %s", target
                    .getClass().getSimpleName()));
        }
        if (contentParent != null) {
            //如果oldContent不等于null，则把oldContent剔除
            contentParent.removeView(oldContent);
        }
        //将 上下文 父类容器 target 所代表的View 还有 target所在父类中的id，传入，创建一个TargetContext
        return new TargetContext(context, contentParent, oldContent, childIndex);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
