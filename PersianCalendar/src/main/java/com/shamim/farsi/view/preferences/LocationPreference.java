package com.shamim.farsi.view.preferences;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

import com.shamim.farsi.Constants;
import com.shamim.farsi.util.Utils;

/**
 * persian_calendar
 * Author: hamidsafdari22@gmail.com
 * Date: 1/17/16
 */
public class LocationPreference extends DialogPreference {

    public LocationPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        Utils.getInstance(getContext()).setFontAndShape(holder);
    }

    public void setSelected(String selected) {
        final boolean wasBlocking = shouldDisableDependents();
        persistString(selected);
        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) notifyDependencyChange(isBlocking);
        LocalBroadcastManager.getInstance(getContext())
                .sendBroadcast(new Intent(Constants.LOCAL_INTENT_UPDATE_PREFERENCE));
    }
}
