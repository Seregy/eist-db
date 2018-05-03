package com.eistdb.composite;

import com.eistdb.semester.Semester;
import com.eistdb.subject.Subject;
import com.eistdb.test.TestKind;

public class SubjectSemester {
    private Subject subject;
    private Semester semester;
    private TestKind testKind;

    public SubjectSemester(Subject subject, Semester semester, TestKind testKind) {
        this.subject = subject;
        this.semester = semester;
        this.testKind = testKind;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public TestKind getTestKind() {
        return testKind;
    }

    public void setTestKind(TestKind testKind) {
        this.testKind = testKind;
    }
}
