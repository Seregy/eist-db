package com.eistdb.student;

import com.eistdb.person.Person;

public class Student {
    private Long id;
    private Person person = new Person();

    private String bookNumber;
    private String note;

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String not) {
        this.note = not;
    }
}
