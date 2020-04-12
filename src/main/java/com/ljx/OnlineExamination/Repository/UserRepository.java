package com.ljx.OnlineExamination.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ljx
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Integer countByPhone(String phone);

    User findByPhoneAndPwd(String phone, String password);

    User findByPhone(String phone);

    User findByUsername(String username);

}
