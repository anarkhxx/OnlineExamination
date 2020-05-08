package com.ljx.OnlineExamination.Service.impl;

import com.ljx.OnlineExamination.Repository.ExampaperRepository;
import com.ljx.OnlineExamination.Repository.PaperquestionRepository;
import com.ljx.OnlineExamination.Repository.QuestionRepository;
import com.ljx.OnlineExamination.Service.ExampaperService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.pojo.Paperquestion;
import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.req.CreatePaperReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyy on 2020/5/3 下午6:39
 */

@Service("ExampaperService")
public class ExampaperServiceImpl implements ExampaperService {
    private final ExampaperRepository exampaperRepository;
    private final PaperquestionRepository paperquestionRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ExampaperServiceImpl(ExampaperRepository exampaperRepository, PaperquestionRepository paperquestionRepository, QuestionRepository questionRepository) {
        this.exampaperRepository = exampaperRepository;
        this.paperquestionRepository = paperquestionRepository;
        this.questionRepository  =questionRepository;
    }


    @Override
    public ServerResponse<Exampaper> create(CreatePaperReq createPaperReq) {

        Exampaper paper = new Exampaper();
        paper.setPname(createPaperReq.getName());
        paper.setDuration(createPaperReq.getDuration());
        paper.setExamdate(createPaperReq.getExamDate());
        paper.setExamtime(createPaperReq.getExamTime());
        paper.setDanxnum(createPaperReq.getDanxNum());
        paper.setDuoxnum(createPaperReq.getDuoxNum());
        paper.setPdnum(createPaperReq.getPdNum());
        paper.setTknum(createPaperReq.getTkNum());
        paper.setZgnum(createPaperReq.getZgNum());

        paper.setTotalpoints(createPaperReq.getTotalPoints());

        paper.setSoap(createPaperReq.getSoup());
        paper.setNote(createPaperReq.getNote());
        exampaperRepository.save(paper);


        Integer pid =  paper.getPid();
        Integer begin = 0;

        if(createPaperReq.getDanxNum()!=null&&createPaperReq.getDanxNum()>0)
        {
            if(randomChooseQuestion(pid,1,begin,createPaperReq.getDanxPoint(),createPaperReq.getDanxNum())) begin+=createPaperReq.getDanxNum();
            else return ServerResponse.createByErrorMessage("单选题不足，组卷失败");
        }
        if(createPaperReq.getDuoxNum()!=null&&createPaperReq.getDuoxNum()>0)
        {
            if(randomChooseQuestion(pid,2,begin,createPaperReq.getDuoxPoint(),createPaperReq.getDuoxNum())) begin+=createPaperReq.getDuoxNum();
            else return ServerResponse.createByErrorMessage("多选题不足，组卷失败");
        }
        if(createPaperReq.getPdNum()!=null&&createPaperReq.getPdNum()>0)
        {
            if(randomChooseQuestion(pid,3,begin,createPaperReq.getPdPoint(),createPaperReq.getPdNum())) begin+=createPaperReq.getPdNum();
            else return ServerResponse.createByErrorMessage("判断题不足，组卷失败");
        }
        if(createPaperReq.getTkNum()!=null&&createPaperReq.getTkNum()>0)
        {
            if(randomChooseQuestion(pid,5,begin,createPaperReq.getTkPoint(),createPaperReq.getTkNum()))  begin+=createPaperReq.getTkNum();
            else return ServerResponse.createByErrorMessage("填空题不足，组卷失败");
        }
        if(createPaperReq.getZgNum()!=null&&createPaperReq.getZgNum()>0)
        {
            if(randomChooseQuestion(pid,4,begin,createPaperReq.getZgPoint(),createPaperReq.getZgNum()))  begin+=createPaperReq.getZgNum();
            else return ServerResponse.createByErrorMessage("主观题不足，组卷失败");
        }

        return ServerResponse.createBySuccessMessage("组卷成功");
    }


    private boolean randomChooseQuestion(Integer pid,Integer typeid,Integer begin,Integer point,Integer num)
    {
        List<Question> list = new ArrayList<>();
        list = (List<Question>)questionRepository.getRandomList(typeid,num);
        if( list==null || list.size() < num )
        {
            exampaperRepository.deleteById(pid);
            return false;
        }

        for(Question q:list)
        {
            begin++;
            Paperquestion question = new Paperquestion();
            question.setPid(pid);
            question.setQid(q.getQid());
            question.setId(begin);
            question.setTypeid(typeid);
            question.setContent(q.getContent());
            question.setOptiona(q.getOptiona());
            question.setOptionb(q.getOptionb());
            question.setOptionc(q.getOptionc());
            question.setOptiond(q.getOptiond());
            question.setImg(q.getImg());
            question.setAnswer(q.getAnswer());
            question.setPoint(point);
            paperquestionRepository.save(question);
        }
        return true;

    }

    @Override
    public ServerResponse getAllPapersName() {
        List<String> list = new ArrayList<>();
        list = exampaperRepository.findAllPname();
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse getQuestionById(Integer id) {
        List<Paperquestion> list = new ArrayList<>();
        list = paperquestionRepository.findByPid(id);
        return ServerResponse.createBySuccess(list);

    }



}
