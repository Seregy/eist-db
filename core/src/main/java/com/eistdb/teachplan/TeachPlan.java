package com.eistdb.teachplan;

import java.time.LocalDate;

public class TeachPlan {
    private Long id;
    private Long semesterId;
    private Long groupId;
    private Long subjectId;
    private Long testerId;
    private Long testKindId;
    private LocalDate testDate;
    private boolean isObligatory;

    public TeachPlan() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTesterId() {
        return testerId;
    }

    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }

    public Long getTestKindId() {
        return testKindId;
    }

    public void setTestKindId(Long testKindId) {
        this.testKindId = testKindId;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public boolean isObligatory() {
        return isObligatory;
    }

    public void setObligatory(boolean obligatory) {
        isObligatory = obligatory;
    }
}
