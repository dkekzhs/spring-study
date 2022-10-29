package com.example.jumptospringboot.Board.Answer;

import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Answer.Service.AnswerService;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @RequestMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @RequestParam String content,
                               @Valid AnswerRequestDto answerRequestDto , BindingResult bindingResult) {
        Question question = this.questionService.getDetailQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.createAnswer(question, answerRequestDto.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
