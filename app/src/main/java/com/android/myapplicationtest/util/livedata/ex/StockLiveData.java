//package com.android.myapplicationtest.util.livedata.ex;
//
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import androidx.annotation.MainThread;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.android.myapplicationtest.util.LogUtil;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class StockLiveData extends LiveData<BigDecimal> {
//
//    private static StockLiveData sInstance;
//    private StockManager stockManager;
//
//    private SimplePriceListener listener = new SimplePriceListener() {
//        @Override
//        public void onPriceChanged(BigDecimal price) {
//            LogUtil.e("change", "StockLiveData price ="+ price);
//            setValue(price);
//        }
//    };
//
//    @MainThread
//    public static StockLiveData getInstance(String symbol) {
//        if (sInstance == null) {
//            sInstance = new StockLiveData(symbol);
//        }
//        return sInstance;
//    }
//
//    private StockLiveData(String symbol) {
//        stockManager = new StockManager(symbol);
//    }
//
//    @Override
//    protected void onActive() {
//        stockManager.requestPriceUpdates(listener);
//    }
//
//    @Override
//    protected void onInactive() {
//        stockManager.removeUpdates(listener);
//    }
//
//
//
//    private class StockManager {
//        private ConcurrentMap<String, SimplePriceListener> map = new ConcurrentHashMap<>();
//        //MutableLiveData<BigDecimal>  mutableLiveData = new MutableLiveData<>();
//        private String symbol;
//        public StockManager(String symbol) {
//              this.symbol = symbol;
//        }
//
//       void requestPriceUpdates(SimplePriceListener listener){
//           map.put(symbol,listener);
//       }
//       void removeUpdates(SimplePriceListener listener){
//           map.remove(listener);
//       }
//    }
//
//    interface SimplePriceListener {
//        void onPriceChanged(BigDecimal price);
//    }
//}
