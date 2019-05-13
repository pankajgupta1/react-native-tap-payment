
package com.tappayment;

import com.tappayment.RNTapPaymentActivity;
import android.content.Intent;
import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;

public class RNTapPaymentModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Callback callback;

  public RNTapPaymentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    reactContext.addActivityEventListener(mActivityEventListener);
  }

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
      // System.out.println(resultCode);
    callback.invoke(resultCode);
    }
  };

  @ReactMethod
  public void openTapPaymentUI(String SecretAPIkey, String AppID, String CustomerId, String Currency, int price, Callback callback1) {
    Activity currentActivity = getCurrentActivity();
    ReactApplicationContext context = getReactApplicationContext();
    callback = callback1;
    Intent intent = new Intent(context, RNTapPaymentActivity.class);
    intent.putExtra("SecretAPIkey", SecretAPIkey);
    intent.putExtra("AppID", AppID);
    intent.putExtra("CustomerId", CustomerId);
    intent.putExtra("price", price);
    intent.putExtra("Currency", Currency);
    currentActivity.startActivityForResult(intent, 1001);
  }

  @Override
  public String getName() {
    return "TapPayment";
  }
}