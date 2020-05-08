package com.ljx.OnlineExamination.Service.impl;

import com.ljx.OnlineExamination.Repository.LabelRepository;
import com.ljx.OnlineExamination.Service.LabelService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyy on 2020/4/17 下午10:20
 */

@Service("LabelService")
public class LabelServiceImpl implements LabelService {


    private final LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }


    @Override
    public ServerResponse getAll() {

        List<Label> list = new ArrayList<>();
        list = labelRepository.findAll();

        return ServerResponse.createBySuccess(list);

    }
}
