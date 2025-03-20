package com.mitocode.repository;


import com.mitocode.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IGenericRepository<User, Integer> {

    //From user u Where u.username = :username
    User findOneByUsername(String username);
}
