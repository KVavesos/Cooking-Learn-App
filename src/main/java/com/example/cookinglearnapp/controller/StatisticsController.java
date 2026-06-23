package com.example.cookinglearnapp.controller;

import com.example.cookinglearnapp.model.UserCompletedLesson;
import com.example.cookinglearnapp.model.UserProgress;
import com.example.cookinglearnapp.service.UserProgressService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    UserProgressService userProgressService;

    @GetMapping("/statistics")
    public String statistics(HttpSession session , Model model) {

        Long userId  = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        UserProgress progress = userProgressService.getProgress(userId);
        model.addAttribute("progress", progress);
        return "statistics" ;
    }

}
