package com.black.jpa;


import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class StudentCard {



    @Id
    @SequenceGenerator(
            name = "studentCardSequence",
            sequenceName = "studentCardSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "studentCardSequence"


    )
    private Long id;

    @Column(
            unique = true,
            nullable = false,
            length = 15
    )
    private  String cardNumber;


    // relationShip fields
    @OneToOne(cascade = CascadeType.ALL ,
            fetch = FetchType.EAGER

    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id" ,// this is the primary key of Student
            foreignKey = @ForeignKey(name = "student_id_fk")

    )
    private Student student;

    public StudentCard() {
    }

    public StudentCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}
