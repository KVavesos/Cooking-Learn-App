package com.example.cookinglearnapp.model;

import jakarta.persistence.*;


@Entity
@Table(name = "user_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "user_id" , unique = true)
    private Long userId;

    @Column (name = "completed_lessons")
    private int completedLessons;

    @Column(name = "total_quiz_attempts")
    private int totalQuizAttempts;

    @Column(name = "correct_answers")
    private int correctAnswers;

    @Column(name = "time_spent")
    private long timeSpent;

    @Column(name = "login_count")
    private int loginCount;

    public UserProgress(){};

    public Long getId(){return id;}

    public Long getUserId() {return userId;}

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }
    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
    }

    public int getTotalQuizAttempts() {return totalQuizAttempts;}
    public void setTotalQuizAttempts (int totalQuizAttempts) {
        this.totalQuizAttempts = totalQuizAttempts;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getLoginCount() {return loginCount;}

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }



}
