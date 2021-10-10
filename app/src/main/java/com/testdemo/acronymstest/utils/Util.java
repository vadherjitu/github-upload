package com.testdemo.acronymstest.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String makeWordsCaptilize(String stringTocaptilize) {
        String retStr = stringTocaptilize;
        try { // We can face index out of bound exception if the string is null
            retStr = stringTocaptilize.substring(0, 1).toUpperCase() + stringTocaptilize.substring(1);
        } catch (Exception e) {
        }
        return retStr;
    }
}
