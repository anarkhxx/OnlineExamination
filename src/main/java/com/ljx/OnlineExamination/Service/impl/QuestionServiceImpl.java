package com.ljx.OnlineExamination.Service.impl;

import com.ljx.OnlineExamination.Repository.ManagerRepository;
import com.ljx.OnlineExamination.Repository.QuestionRepository;
import com.ljx.OnlineExamination.Service.QuestionService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.dto.PageChunk;
import com.ljx.OnlineExamination.pojo.Manager;
import com.ljx.OnlineExamination.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
