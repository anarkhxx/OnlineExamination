package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.req.ExampaperNameReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lyy on 2020/5/3 下午6:33
 */
@Repository
public interface ExampaperRepository extends JpaRepository<Exampaper,Integer>{
    @Query(value="select new com.ljx.OnlineExamination.req.ExampaperNameReq(N.pid, N.pname) from Exampaper N")
    List<ExampaperNameReq> findAllName();

    Exampaper findOneByPid(Integer id);
}


