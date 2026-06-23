package com.example.cookinglearnapp.controller;

import com.example.cookinglearnapp.model.User;
import com.example.cookinglearnapp.model.UserProgress;
import com.example.cookinglearnapp.repository.UserCompletedLessonRepository;
import com.example.cookinglearnapp.repository.UserRepository;
import com.example.cookinglearnapp.service.LessonService;
import com.example.cookinglearnapp.service.UserProgressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cookinglearnapp.service.UserService;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserProgressService userProgressService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userService.findUser(username, password);

        if (user != null) {

            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());

            UserProgress progress =
                    userProgressService.getProgress(user.getId());

            progress.setLoginCount(progress.getLoginCount() + 1);
            userProgressService.save(progress);

            return "redirect:/home";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        model.addAttribute("lessons",lessonService.getAllLessons());

        Set<Long> completedLessons =
                lessonService.getCompletedLessons(userId);

        model.addAttribute(
                "completedLessons",
                completedLessons
        );

        User user = userRepository
                .findById(userId)
                .orElseThrow();

        model.addAttribute(
                "showOnboarding",
                !user.isOnboardingCompleted()
        );

        return "home";
    }


    @GetMapping("/sign-up")
    public String signupPage(){
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registerUser(@RequestParam String username , @RequestParam String password , @RequestParam String email){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/login";
    }


}
