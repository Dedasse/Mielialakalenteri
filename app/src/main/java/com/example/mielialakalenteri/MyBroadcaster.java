package com.example.mielialakalenteri;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.example.mielialakalenteri.R;



public class MyBroadcaster extends BroadcastReceiver{

    Notification noti;
    Uri notif;

    @Override
    public void onReceive(Context context, Intent intent){
        int mode=intent.getIntExtra("mode",0);
        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));

        String channelId="com.example.mielialakalenteri";
        String channelname="ANDROID CHANNEL";
        NotificationChannel channelID=new NotificationChannel(channelId,channelname,NotificationManager.IMPORTANCE_DEFAULT);
        channelID.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        channelID.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        channelID.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        channelID.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channelID);

        Notification noti=new Notification.Builder(context,channelId)
                .setContentTitle("Alarm is ON")
                .setContentText("Time to DO")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        //NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);
        if(mode==0){
        startPlaying(context);
        }else {
            stopPlaying(context);
        }

    }


    public void startPlaying(Context context){
        Uri notif= RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALARM);

        Ringtone r=RingtoneManager.getRingtone(context,notif);
        r.play();
    }

    public void stopPlaying(Context context){
        Uri notif= RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALARM);

        Ringtone r=RingtoneManager.getRingtone(context,notif);
        r.stop();
    }

}
