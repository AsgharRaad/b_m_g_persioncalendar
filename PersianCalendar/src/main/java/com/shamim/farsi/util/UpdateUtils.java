package com.shamim.farsi.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.shamim.farsi.Constants;
import com.shamim.farsi.R;
import com.shamim.farsi.Widget1x1;
import com.shamim.farsi.Widget2x2;
import com.shamim.farsi.Widget4x1;
import com.shamim.farsi.service.ApplicationService;
import com.shamim.farsi.view.activity.MainActivity;
import com.github.praytimes.Clock;
import com.google.android.apps.dashclock.api.ExtensionData;

import java.util.Calendar;
import java.util.Date;

import calendar.CivilDate;
import calendar.DateConverter;
import calendar.PersianDate;


public class UpdateUtils {
    String NOTIFICATION_CHANNEL_ID = "comeback";//new-- add notification channel
    private static NotificationManager mNotificationMgr;

    private static final int NOTIFICATION_ID = 1001;
    private static UpdateUtils myInstance;
    private Context context;
    private PersianDate pastDate;
    private Utils utils;
    private ExtensionData mExtensionData;
    

        if (owghat != null) {
            remoteViews2.setTextViewText(R.id.owghat_2x2, utils.shape(owghat));
            remoteViews2.setViewVisibility(R.id.owghat_2x2, View.VISIBLE);
        } else {
            remoteViews2.setViewVisibility(R.id.owghat_2x2, View.GONE);
        }

        remoteViews2.setTextViewText(R.id.time_2x2, utils.shape(text1));
        remoteViews2.setTextViewText(R.id.date_2x2, utils.shape(text2));

        remoteViews2.setOnClickPendingIntent(R.id.widget_layout2x2, launchAppPendingIntent);
        manager.updateAppWidget(new ComponentName(context, Widget2x2.class), remoteViews2);

        //
        // Permanent Notification Bar and DashClock Data Extension Update
        //
        //
        String status = utils.getMonthName(persian);

        String title = utils.getWeekDayName(civil) + Constants.PERSIAN_COMMA + " " +
                utils.dateToString(persian);

        String body = utils.dateToString(civil) + Constants.PERSIAN_COMMA + " "
                + utils.dateToString(DateConverter.civilToIslamic(civil, utils.getIslamicOffset()));

        // Prepend a right-to-left mark character to Android with sane text rendering stack
        // to resolve a bug seems some Samsung devices have with characters with weak direction,
        // digits being at the first of string on
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            title = Constants.RLM + title;
            body = Constants.RLM + body;
        }

        int icon = utils.getDayIconResource(persian.getDayOfMonth());


        if (Build.VERSION.SDK_INT >= 26) {// new----channel
            CharSequence name = context.getResources().getString(R.string.app_name);// The user-visible name of the channel.
            NotificationChannel NOTIFICATION_CHANNEL = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW);
            NOTIFICATION_CHANNEL.setSound(null, null);
            mNotificationMgr.createNotificationChannel(NOTIFICATION_CHANNEL);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// new----channel
            CharSequence name = context.getResources().getString(R.string.app_name);// The user-visible name of the channel.
            NotificationChannel NOTIFICATION_CHANNEL = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW);
            NOTIFICATION_CHANNEL.setSound(null, null);
            mNotificationMgr.createNotificationChannel(NOTIFICATION_CHANNEL);
        }

(
                context.getPackageName(),
                R.layout.layout_red
        );

        // Locate and set the Image into customnotificationtext.xml ImageViews

//        notificationView.setImageViewResource(
//                R.id.imagenotileft,
//                ic);

        notificationView.setTextViewText(R.id.imagenotileft,String.valueOf(ic));
        // Locate and set the Text into customnotificationtext.xml TextViews
        notificationView.setTextViewText(R.id.title, tit);
        notificationView.setTextViewText(R.id.text, bd);

        return notificationView;
    }


    public ExtensionData getExtensionData() {
        return mExtensionData;
    }

}
