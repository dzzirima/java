package com.black.jpa;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false

    )

    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    private String lastName;
    @Column(
            unique = true
    )
    private String email;

    private Integer age;


    //relationship fields

    @OneToOne(
            mappedBy = "student" ,
            orphanRemoval = true
    )
    private StudentCard studentIdCard;



    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "borrower",
            fetch = FetchType.EAGER
    )
    private List< Book> booksBorrowed = new ArrayList<>();



    private List<Courses>  coursesList = new ArrayList<>();

    public Student(Long id, String firstName, String  lastName, String email, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // additional methods
    // 1 .  setter

    public void addBook(Book book){
        if(!this.booksBorrowed.contains(book)){
            this.booksBorrowed.add(book);
            book.setBorrower(this); // bidirectional relationship
        }
    }

    public void removeBook(Book book){
        if(this.booksBorrowed.contains(book)){
            this.booksBorrowed.remove(book);
            book.setBorrower(null); // remove the reference ...
        }
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", studentIdCard=" + studentIdCard +
                ", booksBorrowed=" + booksBorrowed +
                '}';
    }
}
