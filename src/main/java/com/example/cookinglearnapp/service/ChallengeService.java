package com.example.cookinglearnapp.service;

import com.example.cookinglearnapp.model.Challenge;
import com.example.cookinglearnapp.model.ChallengeCompletion;
import com.example.cookinglearnapp.repository.ChallengeCompletionRepository;
import com.example.cookinglearnapp.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeCompletionRepository completionRepository;

    public ChallengeService(ChallengeRepository challengeRepository,
                            ChallengeCompletionRepository completionRepository) {
        this.challengeRepository = challengeRepository;
        this.completionRepository = completionRepository;
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public long getCompletedCount(Long userId) {
        return completionRepository.countByUserIdAndStatus(userId, "COMPLETED");
    }

    public boolean isCompleted(Long userId, Long challengeId) {
        return completionRepository.existsByUserIdAndChallengeId(userId, challengeId);
    }

    public void completeChallenge(Long userId, Long challengeId) {


        boolean alreadyDone = completionRepository
                .existsByUserIdAndChallengeIdAndStatus(userId, challengeId, "COMPLETED");

        if (alreadyDone) return;

        ChallengeCompletion completion = new ChallengeCompletion();
        completion.setUserId(userId);
        completion.setChallengeId(challengeId);
        completion.setStatus("COMPLETED");

        completionRepository.save(completion);
    }

    public List<Long> getCompletedChallengeIds(Long userId) {
        return completionRepository.findByUserId(userId)
                .stream()
                .map(ChallengeCompletion::getChallengeId)
                .toList();
    }

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));
    }
}