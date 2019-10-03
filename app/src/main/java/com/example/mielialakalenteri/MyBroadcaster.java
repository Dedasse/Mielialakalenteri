package com.example.mielialakalenteri;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.example.mielialakalenteri.R;

public class MyBroadcaster extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){
        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Notification noti=new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("Time to DO")
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);

        Uri notif= RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALARM);

        Ringtone r=RingtoneManager.getRingtone(context,notif);
        r.play();
    }

}
