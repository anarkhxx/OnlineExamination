package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Paperquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lyy on 2020/5/3 下午8:47
 */

@Repository
public interface PaperquestionRepository  extends JpaRepository<Paperquestion,Integer> {


}
