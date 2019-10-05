package com.example.mielialakalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private int mHour;
    private int mMinute;
    GetterSetter getterSetter;
    MyBroadcaster myBroadcaster;
    @Override
    protected void onCreate(Bundle savedInstanceState)    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getterSetter=new GetterSetter(this);
        String save=getterSetter.getPref(this);

        if (save.isEmpty()) {
            setContentView(R.layout.your_day);
        }else{
        imageUpdate();}

    }
    public void onClickBtn(View view) {

        if (view == findViewById(R.id.great)) {
            getterSetter.setPref(this,"great");
            imageUpdate();
        } else if (view== findViewById(R.id.good)) {
            getterSetter.setPref(this,"good");
            imageUpdate();
        }else if (view==findViewById(R.id.ok)) {
            getterSetter.setPref(this,"ok");
            imageUpdate();
        }else if (view==findViewById(R.id.bad)) {
            getterSetter.setPref(this, "bad");
            imageUpdate();
        }else if (view==findViewById(R.id.really_bad)) {
            getterSetter.setPref(this, "verybad");
            imageUpdate();
        }else if(view==findViewById(R.id.button)){
            Intent intent=new Intent(this,Main2Activity.class);
            Context context=getApplicationContext();

            startActivity(intent);
        }else if(view==findViewById(R.id.button2)){
            setTimer(view);

        }else if(view==findViewById(R.id.button4)){
            cancelAlarm();
        }

    }

    public void setTimer(View view){
        TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date=new Date();

        Calendar cal_alarm=Calendar.getInstance();
        Calendar cal_now=Calendar.getInstance();

        cal_now.setTime(date);
        mHour=timePicker.getHour();
        mMinute=timePicker.getMinute();
        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMinute);
        cal_alarm.set(Calendar.SECOND,0);
        Log.d("FUCK",""+mMinute);
        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }

        Intent intent=new Intent(MainActivity.this,MyBroadcaster.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,24444,intent,0);
        intent.putExtra("mode",1);
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);

    }

    public void cancelAlarm(){
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(MainActivity.this,MyBroadcaster.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,24444,intent,0);
        myBroadcaster.stopPlaying();
        alarmManager.cancel(pendingIntent);
    }

    public void imageUpdate(){
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.textView);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        String aa= getterSetter.getPref(this);
        imageView.setImageResource(getImageId(this,aa));

        textView.setText("Feeling: "+aa);

    }

    public static int getImageId(Context context, String imageName) {

        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
