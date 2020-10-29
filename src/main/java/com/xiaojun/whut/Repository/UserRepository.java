package com.xiaojun.whut.Repository;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findByPhoneNumber(String phoneNumber);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Role> getUserByPhoneNumber(String phoneNumber);


}