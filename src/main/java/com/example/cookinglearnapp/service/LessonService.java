package com.example.cookinglearnapp.service;
import com.example.cookinglearnapp.model.Lesson;
import com.example.cookinglearnapp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cookinglearnapp.model.UserCompletedLesson;
import com.example.cookinglearnapp.repository.UserCompletedLessonRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserCompletedLessonRepository completedLessonRepository;

    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void markLessonCompleted(Long userId , Long lessonId){

        boolean exists =
                completedLessonRepository
                        .existsByUserIdAndLessonId(userId, lessonId);

        if (!exists) {

            UserCompletedLesson completedLesson =
                    new UserCompletedLesson();

            completedLesson.setUserId(userId);
            completedLesson.setLessonId(lessonId);

            completedLessonRepository.save(completedLesson);
        }

    }

    public Set<Long> getCompletedLessons(Long userId) {

        List<UserCompletedLesson> completed =
                completedLessonRepository.findByUserId(userId);

        Set<Long> lessonIds = new HashSet<>();

        for (UserCompletedLesson lesson : completed) {
            lessonIds.add(lesson.getLessonId());
        }

        return lessonIds;
    }

    public Lesson getNextLesson(Long id) {
        return lessonRepository.findFirstByIdGreaterThan(id);
    }

    public Lesson getPreviousLesson(Long id) {
        return lessonRepository.findFirstByIdLessThanOrderByIdDesc(id);
    }
}
