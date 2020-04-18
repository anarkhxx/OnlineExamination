package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.LabelService;
import com.ljx.OnlineExamination.common.Const;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Label;
import com.ljx.OnlineExamination.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lyy on 2020/4/17 下午10:18
 */

@RestController
@RequestMapping("api/label")

public class LabelController {
    private final LabelService labelService;
    @Autowired
    public LabelController(LabelService labelService
    ) {

        this.labelService = labelService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ServerResponse<List<Label>> queryAll(
                                       HttpSession session
    ){
        ServerResponse<List<Label>> response = labelService.getAll();

        return response;
    }


}
