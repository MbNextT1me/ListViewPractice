package com.example.customadapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;

    Button sortByNameBtn, sortByPhoneBtn, sortBySexBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        String jsonData = Utils.loadJSONFromAsset(getApplicationContext());
        Gson gson = new Gson();
        Type userType = new TypeToken<List<User>>() {}.getType();
        ArrayList<User> users = gson.fromJson(jsonData, userType);

        adapter = new UserListAdapter(this, users);
        listView.setAdapter(adapter);

        sortByNameBtn = findViewById(R.id.sortByName);
        sortByPhoneBtn = findViewById(R.id.sortByPhone);
        sortBySexBtn = findViewById(R.id.sortBySex);

        sortByNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.sortByName();
            }
        });

        sortByPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.sortByPhone();

            }
        });
        sortBySexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.sortBySex();
            }
        });
    }
}
