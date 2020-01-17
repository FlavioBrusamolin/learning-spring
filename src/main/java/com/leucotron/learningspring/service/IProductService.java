package com.leucotron.learningspring.service;

import java.util.List;

import com.leucotron.learningspring.dto.JProductDTO;

/**
 *
 * @author flavio
 */
public interface IProductService {

    public List<JProductDTO> list();

    public JProductDTO find(Long id);

    public JProductDTO store(JProductDTO dto);

    public JProductDTO delete(Long id);

    public JProductDTO update(Long id, JProductDTO dto);
}
