
package com.tappayment;

import java.util.HashMap;
import java.util.Map;

import com.tappayment.RNTapPaymentActivity;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReadableMap;

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
      if(requestCode == 9123) {
        Bundle extras = intent.getExtras();
        String resultMessage = extras.getString("resultMessage");
        String resultCode2 = extras.getString("resultCode");
        String chargeId = extras.getString("chargeId");
        String custId = extras.getString("custId");
        Double amount = extras.getDouble("amount");
        String currency = extras.getString("currency");
        HashMap<String, String> result = new HashMap<>();
        result.put("resultCode", resultCode2);
        result.put("resultMessage", resultMessage);
        result.put("chargeId", chargeId);
        result.put("custId", custId);
        result.put("amount", Double.toString(amount));
        result.put("currency", currency);
        WritableMap map = new WritableNativeMap();
        for (Map.Entry<String, String> entry : result.entrySet()) {
            map.putString(entry.getKey(), entry.getValue());
        }
        callback.invoke(map);
      }
    }
  };

  @ReactMethod
  public void openTapPaymentUI(ReadableMap readableMap, Callback callback1) {

    Activity currentActivity = getCurrentActivity();
    ReactApplicationContext context = getReactApplicationContext();
    callback = callback1;
    Intent intent = new Intent(context, RNTapPaymentActivity.class);
    intent.putExtra("SecretAPIkey", readableMap.getString("SecretAPIkey"));
    intent.putExtra("AppID", readableMap.getString("AppID"));
    intent.putExtra("CustomerId", readableMap.getString("CustomerId"));
    intent.putExtra("price", readableMap.getInt("price"));
    intent.putExtra("Currency", readableMap.getString("Currency"));
    intent.putExtra("UILanguage", readableMap.getString("UILanguage"));

    ReadableMap Customer = readableMap.getMap("Customer");

    intent.putExtra("firstName", Customer.getString("firstName"));
    intent.putExtra("lastName", Customer.getString("lastName"));
    intent.putExtra("email", Customer.getString("email"));
    intent.putExtra("countryCode", Customer.getString("countryCode"));
    intent.putExtra("phoneNumber", Customer.getString("phoneNumber"));
    currentActivity.startActivityForResult(intent, 9123);
  }

  @Override
  public String getName() {
    return "TapPayment";
  }
}