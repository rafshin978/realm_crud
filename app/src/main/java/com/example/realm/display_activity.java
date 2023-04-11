package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class display_activity extends AppCompatActivity {

    List<DataModal> dataModalList;

    Realm realm;
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.RecyclerView_Course);

        dataModalList = new ArrayList<>();

        dataModalList = realm.where(DataModal.class).findAll();

        recycleViewAdapter = new RecycleViewAdapter(dataModalList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recycleViewAdapter);



    }
}