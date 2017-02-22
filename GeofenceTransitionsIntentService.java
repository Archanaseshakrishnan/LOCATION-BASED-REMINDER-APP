package com.gmail.rsarchana.trial;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by archa on 1/16/2017.
 */

public class GeofenceTransitionsIntentService extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1000;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
       Boolean entering = intent.getBooleanExtra(key, false);
       if (entering) {
        Log.d(getClass().getSimpleName(), "entering");
         }
        else {
            Log.d(getClass().getSimpleName(), "exiting");
        }
        NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, null, 0);
       /* Notification notification = createNotification();
        notification.setLatestEventInfo(context,"Proximity Alert!", "You are near your point of interest.", pendingIntent);*/
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("ALARM")
                        .setContentText("POI!");
        mBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private Notification createNotification() {
        Notification notification = new Notification();
        notification.when = System.currentTimeMillis();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.ledARGB = Color.WHITE;
        notification.ledOnMS = 1500;
        notification.ledOffMS = 1500;
        return notification;
    }

}
