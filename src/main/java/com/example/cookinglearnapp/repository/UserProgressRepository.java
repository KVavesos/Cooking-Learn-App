package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    UserProgress findByUserId(Long userId);
}
