package com.example.sasha.singletask;

import android.app.Activity;
import android.app.FragmentManager;

public class Utils {
    public static void clearBackStack(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}