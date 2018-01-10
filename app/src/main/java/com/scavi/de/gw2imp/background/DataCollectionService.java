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
 *
 */
package com.scavi.de.gw2imp.background;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.background.collector.ItemCollector;
import com.scavi.de.gw2imp.dagger2.component.DaggerDataCollectionServiceComponent;
import com.scavi.de.gw2imp.ui.activity.KeepAliveActivity;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class DataCollectionService extends Service {
    @Inject
    protected ItemCollector mItemPriceCollector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDataCollectionServiceComponent.builder()
                .applicationComponent(getApplicationComponent().getComponent())
                .build()
                .inject(this);
        mItemPriceCollector.start();
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
