package com.minitest2.service.category;

import com.minitest2.model.Category;
import com.minitest2.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepo categoryRepo;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }
}