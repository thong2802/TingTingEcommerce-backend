package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.ProductDTO;
import com.TingTing.ecommerce.model.Category;
import com.TingTing.ecommerce.model.Product;
import com.TingTing.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDTO productDTO, Category category) {
        Product product = getProductFromDto(productDTO, category);
        productRepository.save(product);
    }
    public static Product getProductFromDto(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> Allproducts = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : Allproducts) {
            ProductDTO productDto = getDtoFromProduct(product);
            productDTOs.add(productDto);
        }
        return productDTOs;
    }
    public static ProductDTO getDtoFromProduct(Product product) {
        ProductDTO productDto = new ProductDTO(product);
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public void updateProduct(Integer productId, ProductDTO productDTO) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // throw exception, if product is not exits
        if (!optionalProduct.isPresent()) {
            throw new Exception("product is not present" + productId);
        }
        Product product = optionalProduct.get();
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
    }
}
