package com.eistdb.lesson;

public class Lesson {
    private Long id;
    private LessonKind lessonKind;
    private Long teacherId;
    private String hours;

    public Lesson() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LessonKind getLessonKind() {
        return lessonKind;
    }

    public void setLessonKind(LessonKind lessonKind) {
        this.lessonKind = lessonKind;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
