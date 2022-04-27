package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.model.Category;
import com.TingTing.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategory() {
      return categoryRepo.findAll();
    }
}
