package com.example.mielialakalenteri;


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

/**
 * Luokka joka hoitaa Notifikation luomisen
 * Vibran käyttöön oton
 * ja ringtonemanagerin asettamisen
 */


public class MyBroadcaster extends BroadcastReceiver {

    int x;
    Notification noti;
    private static Uri notif;
    private static Ringtone r;
    @Override
    public void onReceive(Context context, Intent intent){

        String state=intent.getExtras().getString("extra");


        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE)); //puhelin vibran luvat

        String channelId="com.example.mielialakalenteri"; //tästä alkaa notification määrittelyt
        String channelname="ANDROID CHANNEL";
        NotificationChannel channelID=new NotificationChannel(channelId,channelname,NotificationManager.IMPORTANCE_DEFAULT);
        channelID.enableLights(true);
        channelID.enableVibration(true);
        channelID.setLightColor(Color.GREEN);
        channelID.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channelID);

        Notification noti=new Notification.Builder(context,channelId)
                .setContentTitle("Alarm is ON")
                .setContentText("Time to DO")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();


        noti.flags=Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);
        if (state.equals("yes")) {  //Intentin tuoma yes/no hälytys äänrn katkaisuun
            startPlaying(context);
            int x=1;
        }else if(state.equals("no")&&  (x==1)){
            stopPlaying(context);
        }
    }


    public void startPlaying(Context context){
         notif= RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_ALARM);

        r=RingtoneManager.getRingtone(context,notif);
        r.play();
    }

    public void stopPlaying(Context context){
        //todo END ALARM

    }

}
