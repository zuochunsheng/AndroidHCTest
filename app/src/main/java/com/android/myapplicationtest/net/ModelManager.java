package com.android.myapplicationtest.net;

import com.android.myapplicationtest.base.IBaseModel;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:19
 * @description：
 */
public class ModelManager {
    private final ConcurrentHashMap<Class<? extends IBaseModel>, ? extends IBaseModel> DATA_CACHE;

    private ModelManager() {
        DATA_CACHE = new ConcurrentHashMap<>(8);
    }

    /**
     * @return ModelManager单例实例
     */
    public static ModelManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ModelManager INSTANCE = new ModelManager();
    }

    /**
     * 创建获取 Model 层实例
     *
     * @param clazz IBaseModel 子类 class
     */
    @SuppressWarnings("unchecked")
    public <M extends IBaseModel> M create(final Class<M> clazz) {

        IBaseModel model = DATA_CACHE.get(clazz);
        if (model != null) {
            return (M) model;
        }

        synchronized (DATA_CACHE) {
            model = DATA_CACHE.get(clazz);
            if (model == null) {
                try {
                    Constructor<M> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    model = constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return (M) model;
    }
}
