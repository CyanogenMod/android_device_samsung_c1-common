package com.cyanogenmod.C1Parts;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class TouchKeyBacklightTimeout implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/class/misc/notification/led_timeout";

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    /**
     * Restore backlight timeout setting from SharedPreferences. (Write to kernel.)
     * @param context       The context to read the SharedPreferences from
     */
    public static void restore(Context context) {
        if (!isSupported()) {
            return;
        }

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Utils.writeValue(FILE, sharedPrefs.getString(C1Parts.KEY_BACKLIGHT_TIMEOUT, "-1"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Utils.writeValue(FILE, (String) newValue);
        return true;
    }

}
