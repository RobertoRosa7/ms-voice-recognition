package br.com.voicerecognition.repository;

import br.com.voicerecognition.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM tb_product WHERE name = :name", nativeQuery = true)
    Product findByName(String name);
}
