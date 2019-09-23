package com.android.myapplicationtest.net;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:18
 * @description：
 */
public final class ObserveOnMainCallAdapterFactory  extends CallAdapter.Factory{

    private final Scheduler mScheduler;

    public ObserveOnMainCallAdapterFactory(Scheduler scheduler) {
        this.mScheduler = scheduler;
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType != Single.class) {
            return null;
        }
        final CallAdapter<Object, Single<?>> delegate =
                (CallAdapter<Object, Single<?>>) retrofit.nextCallAdapter(this, returnType, annotations);
        return new CallAdapter<Object, Object>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Object adapt(@NonNull Call<Object> call) {
                Single<?> s = delegate.adapt(call);
                return s.observeOn(mScheduler);
            }
        };
    }

    /**
     * 在android主线程处理下游数据
     */
    public static CallAdapter.Factory createMainScheduler() {
        return new ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread());
    }
}
