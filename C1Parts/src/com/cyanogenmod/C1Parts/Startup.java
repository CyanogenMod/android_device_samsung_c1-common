package com.cyanogenmod.C1Parts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Startup extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        TouchKeyBacklightTimeout.restore(context);
        NotificationTimeout.restore(context);
        NotificationEnabled.restore(context);
        Hspa.restore(context);
        mDNIeUIMode.restore(context);
        mDNIeUserMode.restore(context);
    }

}
