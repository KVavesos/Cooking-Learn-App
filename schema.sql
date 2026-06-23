CREATE DATABASE IF NOT EXISTS cooking_db;
USE cooking_db;

--users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    onboarding_completed TINYINT(1),
    reward_claimed TINYINT(1)
);

-- CHALLENGES
CREATE TABLE challenges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    instructions TEXT,
    image VARCHAR(255)
);

-- LESSONS
CREATE TABLE lessons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    content TEXT,
    content1 TEXT,
    content2 TEXT,
    content3 TEXT,
    image_url VARCHAR(255),
    image2_url VARCHAR(255),
    image3_url VARCHAR(255)
);

-- LESSON COMPLETION
CREATE TABLE lesson_completion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    lesson_id BIGINT,
    completed TINYINT(1)
);

-- USER COMPLETED LESSONS
CREATE TABLE user_completed_lessons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    lesson_id BIGINT,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- QUIZ ATTEMPTS
CREATE TABLE quiz_attempts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    lesson_id BIGINT,
    score INT,
    total_questions INT,
    attempt_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- USER PROGRESS
CREATE TABLE user_progress (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    completed_lessons INT DEFAULT 0,
    total_quiz_attempts INT DEFAULT 0,
    correct_answers INT DEFAULT 0,
    time_spent BIGINT DEFAULT 0,
    login_count INT DEFAULT 0
);

CREATE TABLE quiz_questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lesson_id BIGINT NOT NULL,
    question TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255),
    option_d VARCHAR(255),
    correct_answer VARCHAR(10) NOT NULL,
    explanation TEXT,
    FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);

CREATE INDEX idx_lesson_id ON quiz_questions(lesson_id);

CREATE TABLE challenge_completions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    challenge_id BIGINT,
    status VARCHAR(255),
    completed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);