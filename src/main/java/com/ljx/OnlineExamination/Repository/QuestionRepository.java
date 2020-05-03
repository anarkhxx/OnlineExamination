package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {
    @Query(value = "select * from question where typeid=?1  order by RAND() limit ?2 ",nativeQuery = true)
    List<Question> getRandomList(Integer typeid, Integer num);

}
