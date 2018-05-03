package com.eistdb.composite;

import com.eistdb.group.Group;
import com.eistdb.mark.Mark;
import com.eistdb.student.Student;
import com.eistdb.subject.Subject;

public class StudentMark {
    private Student student;
    private Mark mark;
    private Subject subject;
    private Group group;

    public StudentMark(Student student, Mark mark, Subject subject, Group group) {
        this.student = student;
        this.mark = mark;
        this.subject = subject;
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
