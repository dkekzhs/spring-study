package com.example.jumptospringboot.Board.Answer;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Answer.Service.AnswerService;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @RequestMapping("/create/{id}")
    @PreAuthorize("isAuthenticated()")
    public String createAnswer(Model model, @PathVariable("id") Long id, @RequestParam String content,
                               @Valid AnswerRequestDto answerRequestDto , BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getDetailQuestion(id);
        UserSite user = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        Answer build = Answer.builder().question(question).author(user).content(answerRequestDto.getContent()).build();
        this.answerService.createAnswer(build);
        return String.format("redirect:/question/detail/%s", id);
    }
}
