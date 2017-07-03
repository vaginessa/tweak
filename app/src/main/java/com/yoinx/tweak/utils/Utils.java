package com.yoinx.tweak.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import android.os.ServiceManager;

import com.yoinx.tweak.R;

import java.util.Arrays;

import tweakinterface.ITweakInterface;

public class Utils {
    private Context mContext;
    private String LOGTAG;
    private ITweakInterface mTweakService;
    private IBinder mBinder;
    private boolean isBound = false;

    public Utils (Context context) {
        mContext = context;
        LOGTAG = "Tweak";
        //if (!isBound) {
            connectService();
        //}
    }

    public void connectService() {
        try {
            mBinder = ServiceManager.getService("TweakInterface");
        } catch (Exception e) {
            Log.e(LOGTAG, "Unable to connect to service");

        }
        if (mBinder != null) {
            mTweakService = ITweakInterface.Stub.asInterface(mBinder);
            isBound = true;
            Toast.makeText(mContext, "Service connected", Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(mContext, "Service disconnected", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public boolean existFile(String filepath) {
        try {
            return mTweakService.existFile(filepath);
        } catch (RemoteException e) {
            Log.e(LOGTAG, mContext.getString(R.string.remote_service_exception));
            e.printStackTrace();
            return false;
        }
    }
    public boolean canWriteFile(String filepath) {
        try {
            return mTweakService.canWriteFile(filepath);
        } catch (RemoteException e) {
            Log.e(LOGTAG, mContext.getString(R.string.remote_service_exception));
            e.printStackTrace();
            return false;
        }
    }
    public boolean writeFile(String filepath, String text) {
        try {
            return mTweakService.writeFile(filepath, text);
        } catch (RemoteException e) {
            Log.e(LOGTAG, mContext.getString(R.string.remote_service_exception));
            e.printStackTrace();
            return false;
        }
    }
    public String readFile(String filepath) {
        try {
            return mTweakService.readFile(filepath);
        } catch (RemoteException e) {
            Log.e(LOGTAG, mContext.getString(R.string.remote_service_exception));
            e.printStackTrace();
            return "Error";
        }
    }

    public String getVersion() {
        try {
            return mTweakService.getVersion();
        } catch (RemoteException e) {
            Log.e(LOGTAG, mContext.getString(R.string.remote_service_exception));
            e.printStackTrace();
            return "Error";
        }
    }

    public boolean getBool (String filepath) {
        String result = readFile(filepath);
        if (result.equals("1") || result.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public void setBool (String filepath, boolean state) {
        String value = readFile(filepath);
        if (value.equals("1") || value.equals("0")) {
            value = "0";
            if (state == true) {
                value = "1";
            }
            writeFile(filepath, value);
        }

    }
}
