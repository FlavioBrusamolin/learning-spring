package com.leucotron.learningspring.service.impl;

import com.leucotron.learningspring.entity.JUser;
import com.leucotron.learningspring.repository.IUserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author flavio
 */
@Service
public class JUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JUser> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        JUser user = optionalUser.get();
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
