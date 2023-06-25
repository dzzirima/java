package com.black.jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student , Long> {

        Optional<Student> findStudentByEmail(String email);

        // native queries


        @Query(
                nativeQuery = true,
                value = "SELECT * from  student WHERE first_name = ?1 AND age >= ?2"
        )
        List<Student> selectAllStudentsNative(String firstName , Integer age);
        @Transactional
        @Modifying
        @Query("DELETE FROM Student u  where u.id = ?1")
          int deleteStudentById(Long id);



}
