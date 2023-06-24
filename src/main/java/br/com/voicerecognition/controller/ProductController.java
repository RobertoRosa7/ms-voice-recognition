package br.com.voicerecognition.controller;

import br.com.voicerecognition.dto.ProductDto;
import br.com.voicerecognition.model.Product;
import br.com.voicerecognition.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("product")
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody() ProductDto body) {
        Product product = new Product();

        product.setName(body.getName());
        product.setPrice(body.getPrice());
        product.setCreatedAt(LocalDateTime.now());

        productService.save(product);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(productService.findAll());
    }


    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody ProductDto body) {
        Product product = productService.findById(body.getId());

        if (product != null) {
            productService.remove(product);
            Product prod = new Product();

            prod.setName(body.getName());
            prod.setPrice(body.getPrice());
            prod.setCreatedAt(LocalDateTime.now());

            productService.save(prod);

            return ResponseEntity.ok(prod);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Product> remove(@PathVariable() Long id) {
        Product product = productService.findById(id);

        if (product != null) {
            productService.remove(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/find-one/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Product> findByName(@RequestParam String name) {
        Product product = productService.findByName(name);
        return ResponseEntity.ok(product);
    }
}
