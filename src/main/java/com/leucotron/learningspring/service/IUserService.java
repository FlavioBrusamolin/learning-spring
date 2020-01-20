package com.leucotron.learningspring.service;

import com.leucotron.learningspring.dto.JUserDTO;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface IUserService {

    public List<JUserDTO> list();

    public JUserDTO find(Long id);

    public JUserDTO store(JUserDTO dto);

    public JUserDTO delete(Long id);

    public JUserDTO update(Long id, JUserDTO dto);

}
