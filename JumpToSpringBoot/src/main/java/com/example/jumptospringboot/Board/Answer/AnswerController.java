package com.example.jumptospringboot.Board.Answer;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Answer.Service.AnswerService;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
                               @Valid AnswerRequestDto answerRequestDto, BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getDetailQuestion(id);
        UserSite user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        Answer build = Answer.builder().question(question).author(user).content(answerRequestDto.getContent()).build();
        this.answerService.createAnswer(build);
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyAnswer(AnswerRequestDto answerRequestDto, Principal principal, @PathVariable("id") Long id) {
        Answer answer = answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerRequestDto.setContent(answer.getContent());
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyAnswer(@Valid AnswerRequestDto answerRequestDto, BindingResult bindingResult,
                               @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerService.modify(answer, answerRequestDto.getContent());
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteAnswer(Principal principal, @PathVariable("id") Long id) {
        Answer answer = answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다");
        }
        answerService.delete(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String voteAnswer(Principal principal, @PathVariable("id") Long id) {
        Answer answer = answerService.getAnswer(id);
        UserSite user = userService.getUser(principal.getName());
        this.answerService.vote(answer, user);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

}
