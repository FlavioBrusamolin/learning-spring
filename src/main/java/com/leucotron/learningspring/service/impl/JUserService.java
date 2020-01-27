package com.leucotron.learningspring.service.impl;

import com.leucotron.learningspring.dto.JUserDTO;
import com.leucotron.learningspring.entity.JUser;
import com.leucotron.learningspring.exception.JDuplicateEntityException;
import com.leucotron.learningspring.exception.JResourceNotFoundException;
import com.leucotron.learningspring.repository.IUserRepository;
import com.leucotron.learningspring.service.IUserService;
import com.leucotron.learningspring.util.JObjectMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author flavio
 */
@Service
public class JUserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<JUserDTO> list() {
        List<JUser> users = userRepository.findAll();
        return JObjectMapper.mapAll(users, JUserDTO.class);
    }

    @Override
    public JUserDTO find(Long id) {
        Optional<JUser> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new JResourceNotFoundException("Unable to find user id");
        }

        return JObjectMapper.map(user.get(), JUserDTO.class);
    }

    @Override
    public JUserDTO store(JUserDTO dto) {
        validateConstraints(dto);

        JUser user = JObjectMapper.map(dto, JUser.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return JObjectMapper.map(userRepository.save(user), JUserDTO.class);
    }

    @Override
    public JUserDTO delete(Long id) {
        JUserDTO user = find(id);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public JUserDTO update(Long id, JUserDTO dto) {
        JUserDTO user = find(id);

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProfile(dto.getProfile());

        return store(user);
    }

    private void validateConstraints(JUserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new JDuplicateEntityException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new JDuplicateEntityException("User email already exists");
        }
    }

}
