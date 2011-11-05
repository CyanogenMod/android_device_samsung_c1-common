package com.cyanogenmod.C1Parts;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class NotificationTimeout implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/class/misc/notification/notification_timeout";

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    /**
     * Restore notification timeout setting from SharedPreferences. (Write to kernel.)
     * @param context       The context to read the SharedPreferences from
     */
    public static void restore(Context context) {
        if (!isSupported()) {
            return;
        }

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Utils.writeValue(FILE, sharedPrefs.getString(C1Parts.NOTIFICATION_TIMEOUT, "-1"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Utils.writeValue(FILE, (String) newValue);
        return true;
    }

}
