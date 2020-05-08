package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Paperquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperQuestionRepository extends JpaRepository<Paperquestion,Integer> {
    List<Paperquestion> findByPid(Integer id);
}
