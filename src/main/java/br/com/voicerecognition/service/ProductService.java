package br.com.voicerecognition.service;

import br.com.voicerecognition.model.Product;
import br.com.voicerecognition.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(Product prod) {
        productRepository.save(prod);
    }

    public void remove(Product prod) {
        productRepository.delete(prod);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
