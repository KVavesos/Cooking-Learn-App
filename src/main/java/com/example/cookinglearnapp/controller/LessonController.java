package com.example.cookinglearnapp.controller;

import com.example.cookinglearnapp.model.Lesson;
import com.example.cookinglearnapp.repository.LessonRepository;
import com.example.cookinglearnapp.service.LessonService;
import com.example.cookinglearnapp.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private LessonService lessonService;


    @GetMapping("/lesson/{id}")
    public String getLesson(@PathVariable Long id , Model model){
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return "redirect:/home";
        }

        model.addAttribute("lesson", lesson);
        model.addAttribute("nextLesson", lessonService.getNextLesson(id));
        model.addAttribute("prevLesson", lessonService.getPreviousLesson(id));

        return "lesson";
    }

    @GetMapping("/dictionary")
    public String giveDictionary(){
        return "dictionary";
    }

}
