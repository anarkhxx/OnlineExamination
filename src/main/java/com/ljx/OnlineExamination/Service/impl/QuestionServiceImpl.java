package com.ljx.OnlineExamination.Service.impl;
import com.ljx.OnlineExamination.Repository.LabelRepository;
import com.ljx.OnlineExamination.Repository.QuestionRepository;
import com.ljx.OnlineExamination.Service.QuestionService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.dto.PageChunk;
import com.ljx.OnlineExamination.pojo.Label;
import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.req.AddQuestionReq;
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
import java.util.ArrayList;
import java.util.List;


@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository,LabelRepository labelRepository) {
        this.questionRepository = questionRepository;
        this.labelRepository = labelRepository;
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
        List<Integer> typeidList = new ArrayList<Integer>();
        List<Integer> difflevelList = new ArrayList<Integer>();
        List<Integer> labelidList = new ArrayList<Integer>();


        if(queryQuestionReq.getTypeid() != null )
        {
            for(String s : queryQuestionReq.getTypeid()){
                typeidList.add(Integer.parseInt(s));
            }
        }


        for(String s : queryQuestionReq.getDifflevel()){
            difflevelList.add(Integer.parseInt(s));

        }

        for(String s : queryQuestionReq.getLabelid()){
            labelidList.add(Integer.parseInt(s));
        }


        //PageRequest
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        //Specification
        Specification<Question> spec = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                //Predicate可以看成一个完整的查询条件，即sql查询语句中中where后面的部分
                //root.get("typeid")表示获取数据库表中typeid这个字段
                //.in(typeid)类似sql语法中的 IN(typeid)
                //root.<String>get("typeid").in(typeid))返回一个Predicate对象，相当于查询条件 'typeid in (typeid)'
                //使用CriteriaBuilder对象（cb）的and方法 cb.and(XXX) 相当于在where查询条件后面用 ‘and XXX ’的形式拼接
                //predicate.getExpressions().add: 增加predicate的查询语句
                if ( typeidList != null && typeidList.size() > 0) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("typeid").in(typeidList)));
                }

                if (difflevelList!=null) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("difflevel").in(difflevelList)));
                }
                if (labelidList!=null) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("labelid").in(labelidList)));
                }

                return predicate;
            }
        };


        Page<Question> questionPage = questionRepository.findAll(spec,pageRequest);
        return ServerResponse.createBySuccess(getRecommendPageChunk(questionPage));
    }




    @Override
    public ServerResponse addQuestion(AddQuestionReq addQuestionReq) {
        Question question=new Question();
        question.setTypeid(addQuestionReq.getTypeid());
        question.setContent(addQuestionReq.getContent());
        question.setOptiona(addQuestionReq.getOptiona());
        question.setOptionb(addQuestionReq.getOptionb());
        question.setOptionc(addQuestionReq.getOptionc());
        question.setOptiond(addQuestionReq.getOptiond());
        question.setAnswer(addQuestionReq.getAnswer());
        question.setImg(addQuestionReq.getImg());
        question.setDifflevel(addQuestionReq.getDifflevel());
        Label label = labelRepository.findByLabelname(addQuestionReq.getLabelname());

        if(label==null)
        {
            Label temp = new Label();
            temp.setLabelname(addQuestionReq.getLabelname());
            labelRepository.save(temp);
            label = labelRepository.findByLabelname(addQuestionReq.getLabelname());
        }
        question.setLabelid(label.getLabelid());
        questionRepository.save(question);
        return ServerResponse.createBySuccessMessage("添加成功");

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
