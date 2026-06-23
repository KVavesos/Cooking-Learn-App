package com.example.cookinglearnapp.controller;

import com.example.cookinglearnapp.model.Challenge;
import com.example.cookinglearnapp.service.ChallengeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public String challengesPage(HttpSession session,
                                 Model model,
                                 @RequestParam(required = false) String success) {


        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        long completed = challengeService.getCompletedCount(userId);

        List<Challenge> challenges = challengeService.getAllChallenges();

        boolean isFullyCompleted = !challenges.isEmpty()
                && completed == challenges.size();

        model.addAttribute("isFullyCompleted", isFullyCompleted);

        List<Long> completedIds =
                challengeService.getCompletedChallengeIds(userId);

        model.addAttribute("completedIds", completedIds);
        int progress = challenges.isEmpty()
                ? 0
                : (int) ((completed * 100) / challenges.size());

        boolean showReward = !challenges.isEmpty()
                && completed == challenges.size();


        model.addAttribute("challenges", challenges);
        model.addAttribute("progress", progress);
        model.addAttribute("showReward", showReward);
        model.addAttribute("success", success != null);

        return "challenges";
    }

    @GetMapping("/challenge/{id}")
    public String challengeDetails(@PathVariable Long id,
                                   HttpSession session,
                                   Model model) {

        Long userId = (Long) session.getAttribute("userId");

        Challenge challenge = challengeService.getChallengeById(id);

        boolean completed = challengeService.isCompleted(userId, id);

        model.addAttribute("challenge", challenge);
        model.addAttribute("completed", completed);

        return "challenge-details";
    }

    @PostMapping("/challenges/submit/{id}")
    public String submitChallenge(
            @PathVariable Long id,
            HttpSession session,
            @RequestParam("image") MultipartFile file
    ) throws IOException {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        if (challengeService.isCompleted(userId, id)) {
            return "redirect:/challenges";
        }

        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        file.transferTo(new File(uploadDir + fileName));
        challengeService.completeChallenge(userId, id);

        return "redirect:/challenges?success=true";
    }

}
