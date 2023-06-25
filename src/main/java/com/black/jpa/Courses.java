package com.black.jpa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class Courses {

    @SequenceGenerator(
            name = "course_sequence",
            allocationSize = 1,
            sequenceName = "course_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Id
    private  Long id;

    private  String course;

    public Courses(String course) {
        this.course = course;
    }

    public Courses() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", course='" + course + '\'' +
                '}';
    }
}
