package com.leucotron.learningspring.repository;

import com.leucotron.learningspring.entity.JUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author flavio
 */
public interface IUserRepository extends JpaRepository<JUser, Long> {

    public Optional<JUser> findByEmail(String email);

}
