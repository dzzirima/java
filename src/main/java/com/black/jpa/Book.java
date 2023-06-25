package com.black.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class Book {
    public Book(String bookName, Timestamp createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book() {
    }

    public Book(String bookName, Student borrower) {
        this.bookName = bookName;
        this.borrower = borrower;
    }

    @SequenceGenerator(
            name = "book_sequence",
            allocationSize = 1,
            sequenceName = "book_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Id
    private  Long id;
    private  String bookName;
    private Timestamp createdAt;


    // relationShip fields

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(

            name = "borrower",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "borrower_id_fk")
    )
    private Student borrower;

    public Student getBorrower() {
        return borrower;
    }



    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", createdAt=" + createdAt +
                ", borrower=" + borrower +
                '}';
    }
}
