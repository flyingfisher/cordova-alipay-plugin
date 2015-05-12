package com.yuhongtech.cordova.plugins.alipay;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

public class AlipayPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("pay")) {
            final Activity activity = this.cordova.getActivity();
            final String payStr = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    PayTask task = new PayTask(activity);
                    boolean isExist = task.checkAccountIfExist();
                    if(!isExist) return callbackContext.error("wallet not found");

                    String payRst = task.pay(payStr);
                    callbackContext.success(payRst);
                }
            });
            return true;
        }

        callbackContext.error("Unknown action " + action);
        return false;
    }


}
