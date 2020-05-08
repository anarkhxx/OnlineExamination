package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Exampaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamPaperRepository extends JpaRepository<Exampaper,Integer> {
    @Query(value="select pname from exampaper" ,nativeQuery=true)
    List<String> findAllPname();
}
