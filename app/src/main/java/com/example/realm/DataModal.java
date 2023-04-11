package com.example.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModal extends RealmObject {
    @PrimaryKey
    private long id;
    private String courseName;
    private String courseDescription;
    private String courseTracks;
    private String courseDuration;

    public DataModal(){

    }

    public DataModal(long id, String courseName, String courseDescription, String courseDuration, String courseTracks){
        this.id = id;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseTracks = courseTracks;
        this.courseDescription = courseDescription;
    }

    public String getCourseTracks() {return courseTracks;}

    public void setCourseTracks(String courseTracks){ this.courseTracks = courseTracks;}

    public long getId() {return id;}

    public void setId(long id){this.id = id;}

    public String getCourseName() {return courseName;}

    public void setCourseName(String courseName){this.courseName = courseName;}

    public String getCourseDescription(){return courseDescription;}

    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }
    public String getCourseDuration() { return courseDuration; }

    public void setCourseDuration(String courseDuration){this.courseDuration = courseDuration;}
}
