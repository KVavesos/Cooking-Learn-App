package com.example.cookinglearnapp.controller;
import com.example.cookinglearnapp.model.QuizAttempt;
import com.example.cookinglearnapp.model.QuizQuestion;
import com.example.cookinglearnapp.model.UserCompletedLesson;
import com.example.cookinglearnapp.model.UserProgress;
import com.example.cookinglearnapp.repository.UserCompletedLessonRepository;
import com.example.cookinglearnapp.service.LessonService;
import com.example.cookinglearnapp.service.QuizService;
import com.example.cookinglearnapp.service.UserProgressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private UserCompletedLessonRepository userCompletedLessonRepository;
    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserProgressService userProgressService;

    @GetMapping("/quiz/{lessonId}")
    public String showQuiz (@PathVariable Long lessonId, Model model){

        List<QuizQuestion> questions = quizService.getQuestionsByLesson(lessonId);

        model.addAttribute("questions", questions);

        model.addAttribute("lessonId", lessonId);


        return "quiz";
    }

    @PostMapping("quiz/submit/{lessonId}")
    public String submitQuiz(@PathVariable Long lessonId , @RequestParam Map<String, String> answers, HttpSession session , Model model){


        Long userId = (Long) session.getAttribute("userId");
        List<QuizQuestion> questions = quizService.getQuestionsByLesson(lessonId);
        int total_questions = questions.size();
        int score = 0 ;

        for (QuizQuestion question: questions){
            String userAnswer = answers.get("question_"+question.getId());

            if (userAnswer!=null && userAnswer.equals(question.getCorrectAnswer())){
                score++;
            }
        }

        if(score >= total_questions*0.75) {

             userId =
                    (Long) session.getAttribute("userId");

            lessonService.markLessonCompleted(userId, lessonId);

            //userProgressService.incrementCompletedLessons(userId);
        }

        QuizAttempt attempt = new QuizAttempt();

        attempt.setUserId(userId);
        attempt.setLessonId(lessonId);
        attempt.setScore(score);
        attempt.setTotalQuestions(questions.size());
        attempt.setAttemptDate(LocalDateTime.now());

        quizService.saveAttempt(attempt);

        UserProgress progress =
                userProgressService.getProgress(userId);

        progress.setTotalQuizAttempts(
                progress.getTotalQuizAttempts() + 1
        );

        progress.setCorrectAnswers(
                progress.getCorrectAnswers() + score
        );

        if(score >= total_questions*0.75){

            progress.setCompletedLessons(
                    progress.getCompletedLessons() + 1
            );
            model.addAttribute("message",
                    "Great Job! You passed the quiz!");

        } else {

            model.addAttribute("message",
                    "You need more practice. Review the lesson and try again.");
        }

        userProgressService.save(progress);


        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());

        return "quiz-result";
    }


}
