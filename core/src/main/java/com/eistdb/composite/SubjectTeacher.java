package com.eistdb.composite;

import com.eistdb.lesson.Lesson;
import com.eistdb.person.Person;
import com.eistdb.subject.Subject;
import com.eistdb.teachplan.TeachPlan;

public class SubjectTeacher {
    private Lesson lesson;
    private Person teacher;
    private TeachPlan teachPlan;
    private Subject subject;

    public SubjectTeacher(Lesson lesson, Person teacher, TeachPlan teachPlan, Subject subject) {
        this.lesson = lesson;
        this.teacher = teacher;
        this.teachPlan = teachPlan;
        this.subject = subject;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public TeachPlan getTeachPlan() {
        return teachPlan;
    }

    public void setTeachPlan(TeachPlan teachPlan) {
        this.teachPlan = teachPlan;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
