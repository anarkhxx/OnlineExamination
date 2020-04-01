package com.ljx.OnlineExamination.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author ljx
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Integer countByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
