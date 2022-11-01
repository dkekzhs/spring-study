package com.example.jumptospringboot.Board.Question;

import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Domain.QuestionRepository;
import com.example.jumptospringboot.Board.Question.Dto.QuestionRequestDto;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model , @RequestParam(value = "page", defaultValue = "0")int page){
        Page<Question> list = questionService.getList(page);
        model.addAttribute("paging", list);
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

    @GetMapping("/12345")
    public void 문제생성(){
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            Question build = Question.builder().subject(subject).content(content).build();
            this.questionService.createQuestion(build);
        }
    }
}
