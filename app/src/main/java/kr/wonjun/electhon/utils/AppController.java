package kr.wonjun.electhon.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;


public class AppController extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        FacebookSdk.sdkInitialize(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
