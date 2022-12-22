package com.example.jumptospringboot.Board.Question;

import com.example.jumptospringboot.Board.Answer.Dto.AnswerRequestDto;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Dto.QuestionRequestDto;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;


@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> list = questionService.getList(page);
        model.addAttribute("paging", list);
        return "question_list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, AnswerRequestDto answerRequestDto) {
        Question question = questionService.getDetailQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @RequestMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String create_question_html(QuestionRequestDto questionRequestDto) {
        return "question_create";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String create_question(@Valid QuestionRequestDto questionRequestDto, BindingResult bindingResult
            , Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_create";
        }
        UserSite user = this.userService.getUser(principal.getName());
        Question question = Question.builder()
                .subject(questionRequestDto.getSubject())
                .content(questionRequestDto.getContent())
                .author(user)
                .build();

        questionService.createQuestion(question);
        return "redirect:/question/list";
    }

    @GetMapping("/12345")
    public void 문제생성() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            Question build = Question.builder().subject(subject).content(content).build();
            this.questionService.createQuestion(build);
        }
    }

    @GetMapping("/modify/{id}")
    @PreAuthorize("isAuthenticated()")
    public String update(QuestionRequestDto questionRequestDto, @PathVariable("id") Long id , Principal principal){
        Question question = this.questionService.getDetailQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        questionRequestDto.setSubject(question.getSubject());
        questionRequestDto.setContent(question.getContent());
        return "question_form";
    }

    @PostMapping("/modify/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionModify(@Valid QuestionRequestDto questionRequestDto, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Long id ){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        Question question = questionService.getDetailQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionRequestDto.getSubject(), questionRequestDto.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Long id){
        Question question = questionService.getDetailQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        questionService.deleteQuestion(question);
        return "redirect:/";
    }
}
