package com.example.mielialakalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;


public class MainActivity extends AppCompatActivity {

    GetterSetter getterSetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        }

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
