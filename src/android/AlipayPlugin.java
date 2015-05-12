package com.yuhongtech.cordova.plugins.alipay;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import java.lang.Exception;

public class AlipayPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("pay")) {
            final Activity activity = this.cordova.getActivity();
            final String payStr = args.getString(0);
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    PayTask task = new PayTask(activity);
                    try {
                        String pay = task.pay(payStr);
                        callbackContext.success();
                    }catch(Exception ex){
                        callbackContext.error(ex);
                    }
                }
            };
            Thread th = new Thread(run);
            th.start();
            return true;
        }

        callbackContext.error("Unknown action " + action);
        return false;
    }


}
