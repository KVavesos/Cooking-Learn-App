package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizQuestionRepository
        extends JpaRepository<QuizQuestion, Long> {

    List<QuizQuestion> findByLessonId(Long lessonId);
}
