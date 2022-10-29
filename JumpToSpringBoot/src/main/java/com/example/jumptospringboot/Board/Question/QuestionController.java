package com.example.jumptospringboot.Board.Question;

import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Domain.QuestionRepository;
import com.example.jumptospringboot.Board.Question.Dto.QuestionRequestDto;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model){
        List<Question> list = questionService.getList();
        model.addAttribute("questionList", list);
        return "question_list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id , AnswerRequestDto answerRequestDto) {
        Question question = questionService.getDetailQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @RequestMapping("/create")
    public String create_question_html(QuestionRequestDto questionRequestDto){
        return "question_create";
    }

    @PostMapping("/create")
    public String create_question(@Valid QuestionRequestDto questionRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "question_create";
        }
        Question question = Question.builder()
                .subject(questionRequestDto.getSubject())
                .content(questionRequestDto.getContent()).build();
        questionService.createQuestion(question);
        return "redirect:/question/list";
    }
}
