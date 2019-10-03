package com.example.mielialakalenteri;


import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    LocalDate date= LocalDate.now();
    SharedPreferences prefs;
    ListView listView;
    List<String> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prefs=getBaseContext().getSharedPreferences("appdata",MODE_PRIVATE);

        listView=(ListView) findViewById(R.id.listview);
        ArrayList<String> arrayList=new ArrayList<>();
        List<String>lists=new ArrayList<>();


            Map<String, ?> allEntries = prefs.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Log.d("map values", entry.getKey() + " : " + entry.getValue().toString());
                String list = entry.getKey() + ": " + entry.getValue().toString();
                lists.add(list);
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1,lists);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);



    }


}
