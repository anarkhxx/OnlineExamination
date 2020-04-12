package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Page<Question> findAll(Pageable pageable);
}
