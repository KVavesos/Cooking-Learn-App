package com.example.cookinglearnapp.repository;
import com.example.cookinglearnapp.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt,Long>{
}
