package com.ljx.OnlineExamination.Repository;
import com.ljx.OnlineExamination.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository  extends JpaRepository<Manager,Integer> {
    Integer countByUsername(String username);
    Manager findByUsernameAndPwd(String username, String password);
}