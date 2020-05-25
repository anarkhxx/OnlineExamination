package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.req.ExampaperNameReq;
import com.ljx.OnlineExamination.req.StudentPaperReq;
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

    @Query(value="select new com.ljx.OnlineExamination.req.StudentPaperReq(N.pid, N.pname,N.examdate,N.examtime,N.duration,N.totalpoints, N.note) from Exampaper N")
    List<StudentPaperReq> findAllStudentPaper();
}


