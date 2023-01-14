package com.driver.repositories;

import com.driver.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //@Query(value="select * from User a where a.username=:name")
    User findByUsername(String username);
}

