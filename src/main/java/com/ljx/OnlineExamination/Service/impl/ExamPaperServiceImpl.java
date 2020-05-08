package com.ljx.OnlineExamination.Service.impl;

import com.ljx.OnlineExamination.Repository.ExamPaperRepository;
import com.ljx.OnlineExamination.Repository.PaperQuestionRepository;
import com.ljx.OnlineExamination.Service.ExamPaperService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Paperquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ExamPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {

    private final ExamPaperRepository examPaperRepository;
    private final PaperQuestionRepository paperQuestionRepository;
    @Autowired
    public ExamPaperServiceImpl(ExamPaperRepository examPaperRepository,
                                PaperQuestionRepository paperQuestionRepository) {
        this.examPaperRepository = examPaperRepository;
        this.paperQuestionRepository = paperQuestionRepository;
    }


    @Override
    public ServerResponse getAllPapersName() {
        List<String> list = new ArrayList<>();
        list = examPaperRepository.findAllPname();
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse getQuestionById(Integer id) {
        List<Paperquestion> list = new ArrayList<>();
        list = paperQuestionRepository.findByPid(id);
        return ServerResponse.createBySuccess(list);

    }
}
