#  Cooking Learn App

A Spring Boot web application designed to help users learn cooking concepts through lessons, quizzes, and challenges.

The platform combines educational content with interactive features to improve cooking knowledge in a structured and engaging way.
 The app also adapts to user's needs.

---

##  Features

- User registration & login system
-  Cooking lessons with structured content 
-  Interactive quizzes for knowledge testing with hints
-  Cooking challenges system 
-  final reward upon completion of all challenges
-  User progress tracking & statistics
-  Personalized learning experience
-  Static content (images, UI design, etc.)

---

##  Tech Stack

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- HTML, CSS, JavaScript
- Maven

---

## Project Structure

src/

    main-----
       java----
          com.example.cookinglearnapp
              controller(5 controllers)
              model(8 models)
              repository(8 repositories)
              service(5 services)
              CookinglearnappApplication.java
       resources----
          static----
             images
             js
             css
          templates 
             html
          application.properties


## How to Run the Project

### Requirements
Before running the project, make sure you have installed:
- Java 17+
- Maven (or use the included Maven Wrapper)
- MySQL (run in workbench the file "schema.sql")

---

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/cooking-learn-app.git
cd cooking-learn-app
2. Set up the database (local only)
```
### 2. Set up the database (local only)

This project does NOT include database credentials for security reasons.

You need to create your own local database and configure the following environment variables:

DB_URL=jdbc:mysql://localhost:3306/cooking_app  
DB_USERNAME=root  
DB_PASSWORD=your_password

---

### 3. Run the application

**Windows:**
```bash
mvnw.cmd spring-boot:run
```
**Mac/Linux**
```bash
./mvnw spring-boot:run
```

### 4. Open in browser

Open your browser and go to:

http://localhost:8080