package com.example.jumptospringboot.Board.Question.Service;


import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Domain.QuestionRepository;
import com.example.jumptospringboot.Common.Exception.DataNotFoundException;
import com.example.jumptospringboot.User.Domain.UserSite;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private Specification<Question> search(String kw){
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, UserSite> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, UserSite> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getDetailQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }
    public List<Question> getList(){
        return questionRepository.findAll();
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }
    public void deleteAll(){
        questionRepository.deleteAll();
    }
    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }

    public void modify(Question question,String  subject , String content){
        question.modify(subject,content);
        questionRepository.save(question);
    }
    public Page<Question> getList(int page,String kw){
        ArrayList<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("localDateTime"));
        Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));
        Specification<Question> search = search(kw);
        return questionRepository.findAll(search,pageable);
    }

    public void vote(Question question, UserSite userSite) {
        question.getVoter().add(userSite);
        questionRepository.save(question);
    }

}
