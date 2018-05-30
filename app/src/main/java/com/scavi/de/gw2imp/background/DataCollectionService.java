/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scavi.de.gw2imp.background;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.scavi.androidimp.env.android.INotificationManagerAccess;
import com.scavi.androidimp.util.AndroidVersionHelper;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.background.collector.ItemCollector;
import com.scavi.de.gw2imp.background.collector.ItemHistoryCollector;
import com.scavi.de.gw2imp.dagger2.component.DaggerDataCollectionServiceComponent;
import com.scavi.de.gw2imp.ui.activity.KeepAliveActivity;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static android.app.Notification.FLAG_FOREGROUND_SERVICE;
import static android.app.Notification.FLAG_NO_CLEAR;
import static android.app.Notification.FLAG_ONGOING_EVENT;

public class DataCollectionService extends Service {
    private static final int APPLICATION_REFERENCE = 42;
    private static final String NOTIFICATION_CHANNEL = "GW2IMP-NOTIFICATIONS";
    private static final String NOTIFICATION_ID = "73";

    @Inject
    protected ItemCollector mItemPriceCollector;
    @Inject
    protected ItemHistoryCollector mItemHistoryCollector;
    @Inject
    protected INotificationManagerAccess mNotificationManagerAccess;

    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDataCollectionServiceComponent.builder()
                .applicationComponent(getApplicationComponent().getComponent())
                .build()
                .inject(this);
        mItemPriceCollector.start();
        mItemHistoryCollector.start();
    }

    @Override
    public int onStartCommand(final Intent intent,
                              final int flags,
                              final int startId) {
        if (mNotification == null) {
            String channelID = AndroidVersionHelper.createNotificationChannelId(
                    mNotificationManagerAccess,
                    NOTIFICATION_ID,
                    NOTIFICATION_CHANNEL,
                    0);
            NotificationCompat.Builder builder = new NotificationCompat.
                    Builder(this, channelID)
                    .setContentTitle(getString(R.string.app_notification_title))
                    .setContentText(getString(R.string.app_notification_text))
                    .setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher);
            mNotification = builder.build();
            mNotification.flags |= FLAG_FOREGROUND_SERVICE | FLAG_ONGOING_EVENT |
                    FLAG_NO_CLEAR;
        }
        startForeground(APPLICATION_REFERENCE, mNotification);
        // We don't want this service to continue running if it is explicitly stopped / killed
        // by the user.
        return START_NOT_STICKY;


    }

    /**
     * @see android.app.Service#onTaskRemoved(android.content.Intent)
     * <p/>
     * Workaround for: https://code.google.com/p/android/issues/detail?id=53313<br> (Foreground
     * service killed when receiving broadcast after activity swiped away in task list)<br> [
     * similar defects: 62091 , 63618 , 63793 , 104308 ]
     */
    @Override
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onTaskRemoved(Intent rootIntent) {
        // This workaround has the side effect that in case if the user opens the android task
        // manager and swipes the app out, the task manager will be closed too. Due to this, this
        // service was changed to STICKY.
        final Intent intent = new Intent(this, KeepAliveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        // the service is designed to run "infinite" and not get bind to another service
        return null;
    }


    /**
     * @return the application component
     */
    @Nonnull
    protected IApplication getApplicationComponent() {
        return (IApplication) getApplication();
    }
}
