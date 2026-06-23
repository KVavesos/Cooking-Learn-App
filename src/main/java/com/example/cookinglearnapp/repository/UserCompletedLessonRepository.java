package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.UserCompletedLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserCompletedLessonRepository extends JpaRepository<UserCompletedLesson , Long>{

    boolean existsByUserIdAndLessonId(Long userId, Long lessonId);

    List<UserCompletedLesson> findByUserId(Long userId);

}
