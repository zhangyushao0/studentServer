package com.studentDemo.user;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "students")
public class Student extends User {
    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "major")
    private String major;

    @Column(name = "year_of_enrollment")
    private int yearOfEnrollment;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(int yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    // getters, setters, constructors, etc.
}
