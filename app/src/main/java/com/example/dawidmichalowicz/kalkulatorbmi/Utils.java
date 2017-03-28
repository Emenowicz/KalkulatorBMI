package com.example.dawidmichalowicz.kalkulatorbmi;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Dawid Michałowicz on 24.03.2017.
 */

public class Utils {
    public static void hideSoftKeyboard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
    }
}
