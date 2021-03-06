package com.shamim.farsi;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.shamim.farsi.service.ApplicationService;
import com.shamim.farsi.util.UpdateUtils;
import com.shamim.farsi.util.Utils;

public class Widget2x2 extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Utils.getInstance(context).isServiceRunning(ApplicationService.class)) {
            context.startService(new Intent(context, ApplicationService.class));
        }
        UpdateUtils.getInstance(context).update(true);
    }
}
