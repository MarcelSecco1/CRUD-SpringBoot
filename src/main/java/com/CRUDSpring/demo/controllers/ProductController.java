package com.CRUDSpring.demo.controllers;

import com.CRUDSpring.demo.domain.product.Product;
import com.CRUDSpring.demo.domain.product.ProductRepository;
import com.CRUDSpring.demo.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @GetMapping
    public ResponseEntity getAll(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }


    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Validated RequestProduct data){
        Product produto = new Product(data);
        System.out.println(data);
        repository.save(produto);
        return ResponseEntity.ok("Dados Gravados com sucesso!!");
    }


    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody @Validated RequestProduct data){
        Optional<Product> existedProduct = repository.findById(data.id());

        if (existedProduct.isPresent()){
            Product product = existedProduct.get();
            product.setName(data.name());
            product.setPrice(data.price());
            repository.save(product);
            return ResponseEntity.ok("Dados Atualizados com sucesso!!" + "\\n" + product);
        }else {
            throw new EntityNotFoundException();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> existedProduct = repository.findById(id);
        if (existedProduct.isPresent()) {
            Product product = existedProduct.get();
            repository.delete(product);
            return ResponseEntity.ok("Dados Deletados com sucesso!!");
        } else {
            throw new EntityNotFoundException();
        }
    }
}
