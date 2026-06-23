package com.example.cookinglearnapp.service;

import com.example.cookinglearnapp.model.UserCompletedLesson;
import com.example.cookinglearnapp.model.UserProgress;
import com.example.cookinglearnapp.repository.UserCompletedLessonRepository;
import com.example.cookinglearnapp.repository.UserProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository repository;

    @Autowired
    private UserCompletedLessonRepository userCompletedLessonRepository;

    public UserProgress getProgress(Long userId){

        UserProgress progress = repository.findByUserId(userId);

        if (progress == null) {

            progress = new UserProgress();
            progress.setUserId(userId);

            progress.setLoginCount(0);
            progress.setCompletedLessons(0);
            progress.setCorrectAnswers(0);
            progress.setTotalQuizAttempts(0);
            progress.setTimeSpent(0);

            return repository.save(progress);
        }

        return progress;
    }

    public void save(UserProgress progress){

        repository.save(progress);
    }

    public void incrementCompletedLessons(Long userId) {
        UserProgress progress =
                repository.findByUserId(userId);

        progress.setCompletedLessons(
                progress.getCompletedLessons() + 1
        );
        repository.save(progress);
    }
    public List<UserCompletedLesson> getCompletedLessons(Long userId) {
        return userCompletedLessonRepository.findByUserId(userId);
    }
}
