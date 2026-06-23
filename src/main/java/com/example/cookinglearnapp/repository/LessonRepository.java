package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson findFirstByIdGreaterThan(Long id);

    Lesson findFirstByIdLessThanOrderByIdDesc(Long id);

}
