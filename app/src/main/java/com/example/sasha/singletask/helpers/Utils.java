package com.example.sasha.singletask.helpers;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Calendar;

public class Utils {
    private static int userId;

    // ASK: что тут происходит?
    public static void clearBackStack(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public static void setUserId(int userId) {
        Utils.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            int measureHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                measureHeight = item.getMeasuredHeight();
                totalItemsHeight += measureHeight;
            }
            totalItemsHeight += measureHeight;

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return;

        } else {
            return;
        }

    }
    
    public static String getTimeAsString(int minutes) {
        final int hours = minutes / 60;
        minutes = minutes % 60;
        return getTimeAsString(hours, minutes);
    }
    
    public static String getTimeAsString(int hours, int minutes) {
        String timeString = "";

        if (hours != 0) {
            String hoursString;
            if (hours % 10 == 1 && (hours < 10 || hours > 20)) {
                hoursString = " час ";
            } else if ((hours % 10 == 2 || hours % 10 == 3 || hours % 10 == 4)
                    && (hours < 10 || hours > 20)) {
                hoursString = " часа ";
            } else {
                hoursString = " часов ";
            }
            timeString += hours + hoursString;
        }

        if (minutes != 0) {
            String minutesString;
            if (minutes % 10 == 1 && (minutes < 10 || minutes > 20)) {
                minutesString = " минута ";
            } else if ((minutes % 10 == 2 || minutes % 10 == 3 || minutes % 10 == 4)
                    && (minutes < 10 || minutes > 20)) {
                minutesString = " минуты ";
            } else {
                minutesString = " минут ";
            }
            timeString += minutes + minutesString;
        }
        return timeString;
    }

    public static int getTimeAsInt(String timeString) {
        String[] timeParts = timeString.split(" ");
        if (timeParts.length == 4) {
            return new Integer(timeParts[0]) * 60 + new Integer(timeParts[2]);
        } else if (timeParts[1].charAt(0) == 'ч') {
            return new Integer(timeParts[0]) * 60;
        } else {
            return new Integer(timeParts[0]);
        }
    }

    public static boolean isDateEarlierThanNow(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        if (year < c.get(Calendar.YEAR)
                || (year == c.get(Calendar.YEAR) && month < c.get(Calendar.MONTH) + 1)
                || (year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) + 1
                && day < c.get(Calendar.DAY_OF_MONTH))) {
            return true;
        } else {
            return false;
        }
    }

    public static String getCurrentTimeAsString() {
        Calendar c = Calendar.getInstance();
        StringBuilder timeBuilder = new StringBuilder();
        timeBuilder.append(c.get(Calendar.YEAR)).append("-")
                .append(String.valueOf(c.get(Calendar.MONTH) + 1)).append("-")
                .append(c.get(Calendar.DAY_OF_MONTH)).append(" ")
                .append(c.get(Calendar.HOUR)).append(":")
                .append(c.get(Calendar.MINUTE)).append(":")
                .append(c.get(Calendar.SECOND));
        return timeBuilder.toString();
    }
}
