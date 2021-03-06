package com.leucotron.learningspring.service.impl;

import com.leucotron.learningspring.dto.JProductDTO;
import com.leucotron.learningspring.entity.JProduct;
import com.leucotron.learningspring.exception.JDuplicateEntityException;
import com.leucotron.learningspring.exception.JResourceNotFoundException;
import com.leucotron.learningspring.repository.IProductRepository;
import com.leucotron.learningspring.service.IProductService;
import com.leucotron.learningspring.util.JObjectMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author flavio
 */
@Service
public class JProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<JProductDTO> list() {
        List<JProduct> products = productRepository.findAll();
        return JObjectMapper.mapAll(products, JProductDTO.class);
    }

    @Override
    public JProductDTO find(Long id) {
        Optional<JProduct> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new JResourceNotFoundException("Unable to find product id");
        }

        return JObjectMapper.map(product.get(), JProductDTO.class);
    }

    @Override
    public JProductDTO store(JProductDTO dto) {
        validateConstraints(dto);

        JProduct product = JObjectMapper.map(dto, JProduct.class);
        return JObjectMapper.map(productRepository.save(product), JProductDTO.class);
    }

    @Override
    public JProductDTO delete(Long id) {
        JProductDTO product = find(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public JProductDTO update(Long id, JProductDTO dto) {
        JProductDTO product = find(id);

        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setValue(dto.getValue());

        return store(product);
    }

    private void validateConstraints(JProductDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new JDuplicateEntityException("Product name already exists");
        }
    }

}
