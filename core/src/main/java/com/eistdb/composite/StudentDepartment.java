package com.eistdb.composite;

import com.eistdb.department.Department;
import com.eistdb.group.Group;
import com.eistdb.student.Student;

public class StudentDepartment {
    private Student student;
    private Group group;
    private Department department;

    public StudentDepartment(Student student, Group group, Department department) {
        this.student = student;
        this.group = group;
        this.department = department;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
