package com.ljx.OnlineExamination.Repository;

import com.ljx.OnlineExamination.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LabelRepository extends JpaRepository<Label,Integer> {
    Label findByLabelname(String labelname);

}
