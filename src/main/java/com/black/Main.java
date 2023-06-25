package com.black;

import com.black.jpa.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }

    //bootstrapping the application
    @Bean
    CommandLineRunner runner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository
    ) {

        return args -> {

            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().firstName();
            String email = String.format("%s.%s@zirima.edu",firstName,lastName);

            Student student = new Student(
                    firstName ,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55)
            );

            Book book = new Book(
                    "introduction to chicken habbits"
            );

            student.addBook(new Book("Intro to chicken volume1 "));
            student.addBook(new Book("Intro to chicken volume 2 "));

            studentRepository.save(student);
            System.out.println("davidz");
            
            studentRepository.findById(1L).ifPresent(student1 -> {
                System.out.println("student1 = ");
                List<Book> books = student1.getBooksBorrowed();
                books.forEach(book1 -> System.out.println(book1.getBookName()));

            });
            
            



//            Optional<Book> byId = bookRepository.findById(1L);
//            System.out.println("byId = " + byId);
//
//            Optional<Student> studentById = studentRepository.findById(1L);
//
//            System.out.println("studentById = " + studentById);

        };

    }

    private static void paginationFunction(StudentRepository studentRepository) {
        PageRequest pageRequest =  PageRequest.of(
                0,
                4,
                Sort.by("firstName").ascending()
        );

        Page<Student> allStudent = studentRepository.findAll(pageRequest);
        System.out.println("allStudent = " + allStudent.getContent());
    }

    private static void sortingStudent(StudentRepository studentRepository) {
        List<Student> students = studentRepository.findAll(
                Sort.by( "firstName").ascending().and(
                Sort.by("age").descending()
        ));
        for (Student student : students) {
            System.out.println(student.getFirstName() + " "+ student.getAge());

        }
    }

    private static void extracted(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().firstName();
            String email = String.format("%s.%s@zirima.edu",firstName,lastName);

            Student student = new Student(
                    firstName ,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55)
            );
            studentRepository.save(student);

        }
    }


}
