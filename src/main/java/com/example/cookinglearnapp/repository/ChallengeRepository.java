package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}