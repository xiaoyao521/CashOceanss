package com.kingja.loadsir.core;

import androidx.annotation.NonNull;

import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    /**
     * @param target           目标Activirty
     * @param onReloadListener 重载监听
     * @param convertor        可以为null
     * @param <T>
     * @return
     */
    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener,
                                    Convertor<T> convertor) {
        //targetContext主要是用来确认target在他所在父容器中的位置
        TargetContext targetContext = LoadSirUtil.getTargetContext(target);
        // convertor 可以为null
        // onReloadListener 重新加载回调
        // builder callback回调的list存储容器
        return new LoadService<>(convertor, targetContext, onReloadListener, builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }


    //构建者模式
    //Builder主要是用来存储callback对象的
    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        public Builder addCallback(@NonNull Callback callback) {
            callbacks.add(callback);
            return this;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<Callback> getCallbacks() {
            return callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            //getDefault是用来创建单例的LoadSir对象
            //setBuilder 是将Builder对象赋值给LoadSir对象
            getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

    }
}
