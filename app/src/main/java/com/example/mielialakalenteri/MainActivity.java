package com.example.mielialakalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {

    private int mHour;
    private int mMinute;
    TimePicker timePicker;
    AlarmManager alarmManager;
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
            getterSetter.setPref(this,"great,");
            imageUpdate();
        } else if (view== findViewById(R.id.good)) {
            getterSetter.setPref(this,"good,");
            imageUpdate();
        }else if (view==findViewById(R.id.ok)) {
            getterSetter.setPref(this,"ok,");
            imageUpdate();
        }else if (view==findViewById(R.id.bad)) {
            getterSetter.setPref(this, "bad,");
            imageUpdate();
        }else if (view==findViewById(R.id.really_bad)) {
            getterSetter.setPref(this, "verybad,");
            imageUpdate();
        }else if(view==findViewById(R.id.history)){
            Intent intent=new Intent(this,Main2Activity.class);
            Context context=getApplicationContext();
            startActivity(intent);
        }else if(view==findViewById(R.id.setalarm)){
            setTimer(view);
            Toast.makeText(this, "Alarm Set",
                    Toast.LENGTH_SHORT).show();

        }else if(view==findViewById(R.id.cancel)){
            cancelAlarm();
            Toast.makeText(this, "Alarm cancelled",
                    Toast.LENGTH_SHORT).show();
        }else if(view==findViewById(R.id.revaluate)){
            setContentView(R.layout.your_day);
        }else if(view==findViewById(R.id.notebook)){
            setContentView(R.layout.notepad);
            String saved=getterSetter.getPref(this);
            String[] savedT=saved.split(",",2);
            TextView textView=(TextView)findViewById(R.id.descriptionEditText);
            TextView dateTxt=(TextView)findViewById(R.id.titleEditText);
            dateTxt.setText(LocalDate.now().toString());
            textView.setText(savedT[1]);
        }else if (view==findViewById(R.id.Save)){
           String save= getterSetter.getPref(this);
           EditText nsave=findViewById(R.id.descriptionEditText);
          String[] saveT=save.split(",",2);
            saveT[1]=nsave.getText().toString();
            save= Arrays.stream(saveT).collect(Collectors.joining(","));
           getterSetter.setPref(this,save);
           setContentView(R.layout.activity_main);
        }

    }

    public void setTimer(View view){
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date=new Date();

        Calendar cal_alarm=Calendar.getInstance();
        Calendar cal_now=Calendar.getInstance();

        cal_now.setTime(date);
        mHour=timePicker.getHour();
        mMinute=timePicker.getMinute();
        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMinute);
        cal_alarm.set(Calendar.SECOND,0);

        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }

        Intent intent=new Intent(MainActivity.this,MyBroadcaster.class);
        intent.putExtra("extra","yes");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,24444,intent,0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);

    }

    public void cancelAlarm(){
        alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(MainActivity.this,MyBroadcaster.class);
        intent.putExtra("extra","no");
        sendBroadcast(intent);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,24444,intent,0);

        alarmManager.cancel(pendingIntent);
    }

    public void imageUpdate(){
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.textView);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        String aa= getterSetter.getPref(this);
        String[] image= aa.split(",",2);
        imageView.setImageResource(getImageId(this,image[0]));

        textView.setText("Feeling: ");

    }

    public static int getImageId(Context context, String imageName) {

        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
