package com.github.shewaeger.bookschecker.repositories;

import com.github.shewaeger.bookschecker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByLogin(String login);
}
