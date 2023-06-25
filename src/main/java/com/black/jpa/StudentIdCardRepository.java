package com.black.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentIdCardRepository extends JpaRepository<StudentCard , Long> {
}
