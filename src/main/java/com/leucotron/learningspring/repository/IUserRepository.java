package com.leucotron.learningspring.repository;

import com.leucotron.learningspring.entity.JUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author flavio
 */
public interface IUserRepository extends JpaRepository<JUser, Long> {

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
    
    public JUser findByUsername(String username);

}
