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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
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

        if(queryQuestionReq.getDifflevel() != null ) {
            for (String s : queryQuestionReq.getDifflevel()) {
                difflevelList.add(Integer.parseInt(s));

            }
        }

        if(queryQuestionReq.getLabelid() != null ) {
            for (String s : queryQuestionReq.getLabelid()) {
                labelidList.add(Integer.parseInt(s));
            }
        }


        //PageRequest
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        //Specification
        Specification<Question> spec = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if ( typeidList != null && typeidList.size() > 0) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("typeid").in(typeidList)));
                }

                if (difflevelList!=null && difflevelList.size() > 0) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("difflevel").in(difflevelList)));
                }
                if (labelidList!=null &&  labelidList.size() > 0) {
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
        question.setDifflevel(addQuestionReq.getDifflevel());

        //图片
        String imgUrl = saveImg(addQuestionReq.getImg());
        question.setImg(imgUrl);

        //标签
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


    public String saveImg(MultipartFile file) {
        String deposeFilesDir = "/root/img/";

        // 获取附件原名
        String fileName = file.getOriginalFilename();
        // 如果是获取的含有路径的文件名，那么截取掉多余的，只剩下文件名和后缀名
        int index = fileName.lastIndexOf("\\");
        if (index > 0) {
            fileName = fileName.substring(index + 1);
        }

        // 有后缀名时
        if (fileName.indexOf(".") >= 0) {
            // split()中放正则表达式; 转义字符"\\."代表 "."
            String[] fileNameSplitArray = fileName.split("\\.");
            // 加上random戳,防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }

        // 无后缀名时
        if (fileName.indexOf(".") < 0) {
            // 加上random戳,防止附件重名覆盖原文件
            fileName = fileName + (int) (Math.random() * 100000);
        }

        //File对象dest
        File dest = new File(deposeFilesDir + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //写入
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return deposeFilesDir + fileName;
    }
}



