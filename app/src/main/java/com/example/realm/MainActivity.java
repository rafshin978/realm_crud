package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    EditText courseName,courseDuration,courseDescription,courseTrack;
    Button b1;
    Button btn2;
    private Realm realm;
    private String name,duration,description,track;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        courseName = findViewById(R.id.txtcoursename);
        courseDuration = findViewById(R.id.txtcourseduration);
        courseDescription = findViewById(R.id.txtcoursedescription);
        courseTrack = findViewById(R.id.txtcoursetracks);
        b1 = findViewById(R.id.button2);
        btn2 = findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,display_activity.class);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = courseName.getText().toString().trim();
                duration = courseDuration.getText().toString().trim();
                description = courseDescription.getText().toString().trim();
                track = courseTrack.getText().toString().trim();

                if (TextUtils.isEmpty(track)){
                    courseTrack.setError("Please enter the course track");
                } else if (TextUtils.isEmpty(name)) {
                    courseName.setError("Please enter the name");
                } else if (TextUtils.isEmpty(duration)) {
                    courseDuration.setError("Please enter course duration");
                } else if (TextUtils.isEmpty(description)) {
                    courseDescription.setError("Please enter course description");
                }else {
                    addDataToDatabase (name,duration,description,track);
                    Toast.makeText(MainActivity.this,"Course added to database",Toast.LENGTH_LONG);
                    courseName.setText("");
                    courseDuration.setText("");
                    courseDescription.setText("");
                    courseTrack.setText("");
                }
            }
        });
    }

    private void addDataToDatabase(String name, String duration, String description, String track) {
        DataModal modal = new DataModal();

        Number id = realm.where(DataModal.class).max("id");

        long nextId;

        if (id == null){
            nextId = 1;
        } else {
            nextId = id.intValue() + 1;
        }
        modal.setId(nextId);
        modal.setCourseDescription(description);
        modal.setCourseName(name);
        modal.setCourseDuration(duration);
        modal.setCourseTracks(track);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(modal);
            }
        });
    }
}