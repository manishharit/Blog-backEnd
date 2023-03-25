package com.maniBlog.BlogbackEnd.Repository;

import com.maniBlog.BlogbackEnd.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username,String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameOrEmail(String username,String email);

}
