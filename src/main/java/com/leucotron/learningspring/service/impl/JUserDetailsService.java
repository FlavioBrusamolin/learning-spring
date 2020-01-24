package com.leucotron.learningspring.service.impl;

import com.leucotron.learningspring.entity.JUser;
import com.leucotron.learningspring.repository.IUserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        JUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        var authorities = new ArrayList<GrantedAuthority>() {
            {
                add(new SimpleGrantedAuthority(user.getProfile().name()));
            }
        };

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
