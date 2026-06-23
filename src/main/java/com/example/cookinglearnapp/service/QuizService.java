package com.example.cookinglearnapp.service;


import com.example.cookinglearnapp.model.QuizAttempt;
import com.example.cookinglearnapp.model.QuizQuestion;
import com.example.cookinglearnapp.repository.QuizAttemptRepository;
import com.example.cookinglearnapp.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public List<QuizQuestion> getQuestionsByLesson(Long lessonId) {

        return quizQuestionRepository.findByLessonId(lessonId);
    }

    public void saveAttempt(QuizAttempt attempt){
        quizAttemptRepository.save(attempt);

    }

}