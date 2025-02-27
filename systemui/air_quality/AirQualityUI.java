package com.android.systemui.air_quality;

import com.android.systemui.CoreStartable;
import android.content.Context;
import com.android.systemui.broadcast.BroadcastDispatcher;
import android.content.BroadcastReceiver;
import dagger.Lazy;
import android.os.Handler;
import android.os.PowerManager;
import android.database.ContentObserver;
import java.util.Optional;
import javax.inject.Inject;

import com.android.systemui.dagger.SysUISingleton;
import android.util.Log;
import android.util.Slog;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.content.ContentResolver;
import android.os.SystemProperties;
import java.lang.Runnable;
import android.app.Notification;
import com.android.systemui.util.NotificationChannels;
import com.android.systemui.SystemUIApplication;
import android.app.NotificationManager;
import android.os.UserHandle;
import com.android.internal.messages.nano.SystemMessageProto.SystemMessage;
import com.android.systemui.R;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.List;


@SysUISingleton
public class AirQualityUI implements CoreStartable {

    static final String TAG = "AirQualityUI";
    static final boolean DEBUG = Log.isLoggable(TAG, Log.DEBUG);

    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final BroadcastDispatcher mBroadcastDispatcher;
    private static final int POLL_INTERVAL_MS = 10000;
    private String lastValue = null;
    private boolean show = true;
    private final NotificationManager mNoMan;
    private SensorManager sensorManager;
    private String sensorListString = "";

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // The value of the first subscript in the values array is the current light intensity
            float value = event.values[2];
            Log.d(TAG, "Valores recebidos: " + value);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Inject
    public AirQualityUI(Context context, BroadcastDispatcher broadcastDispatcher) {
        mContext = context;
        mBroadcastDispatcher = broadcastDispatcher;
        mNoMan = mContext.getSystemService(NotificationManager.class);
    }

    

    @Override
    public void start() {
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_DEVICE_PRIVATE_BASE);

        String sensorListString = "";
        for (Sensor el : sensorList)
            sensorListString += el.getName() + " " + el.getStringType() + "\n";
        Log.d(TAG, "AIR_QUALITY_TEST" + sensorListString);

        Sensor sensor = sensorList.get(0);

        Log.d(TAG, "Foi aqui 1" + sensorListString);

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "Foi aqui 2" + sensorListString);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentValue = SystemProperties.get("debug.tracing.screen_brightness");
                if(Float.parseFloat(currentValue) <= 0.5) {
                    final Notification.Builder nb =
                            new Notification.Builder(mContext, NotificationChannels.BATTERY)
                                    .setSmallIcon(R.drawable.ic_power_low)
                                    .setContentTitle("Alerta: Qualidade do Ar Crítica");
                    SystemUIApplication.overrideNotificationAppName(mContext, nb, false);
                    final Notification n = nb.build();
                    mNoMan.cancelAsUser(TAG, SystemMessage.NOTE_POWER_LOW, UserHandle.ALL);
                    mNoMan.notifyAsUser(TAG, SystemMessage.NOTE_BAD_CHARGER, n, UserHandle.ALL);
                }
                mHandler.postDelayed(this, POLL_INTERVAL_MS);
            }
        }, POLL_INTERVAL_MS);

        Log.d(TAG, "Chegou aqui!");
    }    

}