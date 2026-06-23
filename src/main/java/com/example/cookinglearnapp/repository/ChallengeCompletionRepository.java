package com.example.cookinglearnapp.repository;

import com.example.cookinglearnapp.model.ChallengeCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeCompletionRepository extends JpaRepository<ChallengeCompletion, Long> {

    List<ChallengeCompletion> findByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);

    boolean existsByUserIdAndChallengeId(Long userId, Long challengeId);

    boolean existsByUserIdAndChallengeIdAndStatus(Long userId, Long challengeId, String status);

}