package com.yuhongtech.cordova.plugins.alipay;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.alipay.sdk.app.PayTask;

public class AlipayPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("pay")) {
            final Activity activity = this.cordova.getActivity();
            final String payStr = args.getString(0);

            boolean isWalletExists = this.appInstalled("com.eg.android.AlipayGphone");
            if(!isWalletExists){
                callbackContext.error("wallet not found");
                return;
            }

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    PayTask task = new PayTask(activity);
                    String payRst = task.pay(payStr);
                    callbackContext.success(payRst);
                }
            });
            return true;
        }

        callbackContext.error("Unknown action " + action);
        return false;
    }

    private boolean appInstalled(String uri) {
        Context ctx = this.cordova.getActivity().getApplicationContext();
        final PackageManager pm = ctx.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch(PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
