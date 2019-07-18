package org.sid.maaboecomv1.web;

import org.sid.maaboecomv1.dao.ProductRepository;
import org.sid.maaboecomv1.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {
    private ProductRepository productRepository;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProduct(@PathVariable("id") Long id) throws Exception{
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
    }
}
