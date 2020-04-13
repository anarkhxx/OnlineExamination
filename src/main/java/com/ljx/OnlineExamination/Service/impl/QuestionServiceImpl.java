package com.ljx.OnlineExamination.Service.impl;
import com.ljx.OnlineExamination.Repository.QuestionRepository;
import com.ljx.OnlineExamination.Service.QuestionService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.dto.PageChunk;
import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.req.QueryQuestionReq;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }



    @Override
    public ServerResponse getAllQuestion(Integer pageNumber,Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<Question> questionPage = questionRepository.findAll(pageRequest);
        return ServerResponse.createBySuccess(getRecommendPageChunk(questionPage));


    }


    @Override
    public ServerResponse getQuestion(QueryQuestionReq queryQuestionReq) {
        Integer pageNumber = queryQuestionReq.getPageNumber();
        Integer pageSize = queryQuestionReq.getPageSize();
        Integer typeid = queryQuestionReq.getTypeid();
        Integer difflevel = queryQuestionReq.getDifflevel();
        Integer labelid = queryQuestionReq.getLabelid();

        //PageRequest
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        //Specification
        Specification<Question> spec = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if ( typeid != null) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("typeid").in(typeid)));
                }

                if (difflevel!=null) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("difflevel").in(difflevel)));
                }
                if (labelid!=null) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("labelid").in(labelid)));
                }

                return predicate;
            }
        };


        Page<Question> questionPage = questionRepository.findAll(spec,pageRequest);
        return ServerResponse.createBySuccess(getRecommendPageChunk(questionPage));
    }



    private PageChunk<Question> getRecommendPageChunk(Page<Question> questionPage) {
        PageChunk<Question> pageChunk = new PageChunk<>();
        pageChunk.setContent(questionPage.getContent());
        pageChunk.setTotalPages(questionPage.getTotalPages());
        pageChunk.setTotalElements(questionPage.getTotalElements());
        pageChunk.setPageNumber(questionPage.getPageable().getPageNumber() + 1);
        pageChunk.setNumberOfElements(questionPage.getNumberOfElements());
        return pageChunk;
    }
}
