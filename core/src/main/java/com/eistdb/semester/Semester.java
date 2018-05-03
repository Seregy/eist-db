package com.eistdb.semester;

import java.time.LocalDate;

public class Semester {
    private Long id;
    private LocalDate teachBegin;
    private LocalDate teachEnd;
    private LocalDate sessionBegin;
    private LocalDate sessionEnd;
    private LocalDate attest1;
    private LocalDate attest2;

    public Semester() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTeachBegin() {
        return teachBegin;
    }

    public void setTeachBegin(LocalDate teachBegin) {
        this.teachBegin = teachBegin;
    }

    public LocalDate getTeachEnd() {
        return teachEnd;
    }

    public void setTeachEnd(LocalDate teachEnd) {
        this.teachEnd = teachEnd;
    }

    public LocalDate getSessionBegin() {
        return sessionBegin;
    }

    public void setSessionBegin(LocalDate sessionBegin) {
        this.sessionBegin = sessionBegin;
    }

    public LocalDate getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalDate sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public LocalDate getAttest1() {
        return attest1;
    }

    public void setAttest1(LocalDate attest1) {
        this.attest1 = attest1;
    }

    public LocalDate getAttest2() {
        return attest2;
    }

    public void setAttest2(LocalDate attest2) {
        this.attest2 = attest2;
    }
}
