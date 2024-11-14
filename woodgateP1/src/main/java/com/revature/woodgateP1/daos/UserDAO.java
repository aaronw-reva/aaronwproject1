package com.revature.woodgateP1.daos;

import com.revature.woodgateP1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
