package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {

    EditText CourseDesc,CourseName,CourseDur,CourseTrack;
    private Button updateCourseBtn,deleteCourseBtn;
    private Realm realm;

    private String courseName,courseDesc,courseDur,courseTrack;

    private long id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        CourseName = findViewById(R.id.idEdtCourseName);
        CourseDesc = findViewById(R.id.idEdtCourseDescription);
        CourseDur = findViewById(R.id.idEdtCourseDuration);
        CourseTrack = findViewById(R.id.idEdtCourseTracks);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDeleteCourse);
        realm = Realm.getDefaultInstance();


       // Intent intent = getIntent();

        courseName = getIntent().getStringExtra("coursename");
        courseDur = getIntent().getStringExtra("courseduration");
        courseDesc = getIntent().getStringExtra("coursedescription");
        courseTrack = getIntent().getStringExtra("coursetrack");
        id = getIntent().getLongExtra("id",0);

        CourseName.setText(courseName);
        CourseDur.setText(courseDur);
        CourseDesc.setText(courseDesc);
        CourseTrack.setText(courseTrack);


        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse(id);
                startActivity(new Intent(UpdateActivity.this,display_activity.class));
            }
        });




        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = CourseName.getText().toString();
                String courseDesc = CourseDesc.getText().toString();
                String courseTrack = CourseTrack.getText().toString();
                String courseDur = CourseDur.getText().toString();

                if (TextUtils.isEmpty(courseName)){
                    CourseName.setError("Invalid text");
                } else if (TextUtils.isEmpty(courseDesc)) {
                    CourseDesc.setError("Invalid text");
                } else if (TextUtils.isEmpty(courseDur)) {
                    CourseDur.setError("Invalid text");
                } else if (TextUtils.isEmpty(courseTrack)) {
                    CourseTrack.setError("Invalid text");
                }else {
                    final  DataModal modal = realm.where(DataModal.class).equalTo("id",id).findFirst();
                    updateCourse(modal,courseName,courseDur,courseDesc,courseTrack);
                }
                startActivity(new Intent(UpdateActivity.this,display_activity.class));

            }
        });



    }

    private void updateCourse(DataModal modal, String courseName, String courseDur, String courseDesc, String courseTrack) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.setCourseName(courseName);
                modal.setCourseDuration(courseDur);
                modal.setCourseTracks(courseTrack);
                modal.setCourseDescription(courseDesc);

                realm.copyToRealmOrUpdate(modal);
            }
        });


    }

    private void deleteCourse(long id) {
        DataModal modal = realm.where(DataModal.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.deleteFromRealm();
            }
        });
    }
}