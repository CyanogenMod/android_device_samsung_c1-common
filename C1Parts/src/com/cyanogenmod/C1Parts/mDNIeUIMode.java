package com.cyanogenmod.C1Parts;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class mDNIeUIMode implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/class/mdnieset_ui/switch_mdnieset_ui/mdnieset_ui_file_cmd";

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    /**
     * Restore Mdnie ui setting setting from SharedPreferences. (Write to kernel.)
     * @param context       The context to read the SharedPreferences from
     */
    public static void restore(Context context) {
        if (!isSupported()) {
            return;
        }

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Utils.writeValue(FILE, sharedPrefs.getString(C1Parts.MDNIE_UI, "0"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Utils.writeValue(FILE, (String) newValue);
        return true;
    }

}
