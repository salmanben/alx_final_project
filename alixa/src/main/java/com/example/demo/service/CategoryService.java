package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.util.HandleImage;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final HandleImage handleImage;

    public CategoryService(CategoryRepository categoryRepository, HandleImage handleImage) {
        this.categoryRepository = categoryRepository;
        this.handleImage = handleImage;
    }

    public List<Category> getAllCategories() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryRepository.findAll(sort);
    }

    public List<Category> getAllActiveCategories() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryRepository.findAllByStatus(true, sort);
    }

    public void saveCategory(CategoryDto categorydto) {
        MultipartFile image = categorydto.getImage();
        String imageUrl = "";
        try{
            imageUrl  = this.handleImage.uploadImage(image);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            
        }

        Category category = new Category(categorydto.getName(), categorydto.getDescription(), imageUrl,
                categorydto.getStatus());
        categoryRepository.save(category);
    }

    public CategoryDto getCategory(int id) {
        Category category = null;
        try {
            category = categoryRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }

        return new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getStatus());
    }

    public Category getCategoryInstance(int id) {
        Category category = null;
        try {
            category = categoryRepository.findById(id).get();
            return category;
        } catch (Exception e) {
            return null;
        }

    }

    public void updateCategory(int id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).get();
        MultipartFile image = categoryDto.getImage();
        String imageUrl = "";
        if (!image.isEmpty()) {
            try{
                String filename = category.getImage().substring(category.getImage().lastIndexOf("/") + 1);
                this.handleImage.deleteImage(filename);
                imageUrl  = this.handleImage.uploadImage(image);
            }
            catch(IOException e){
                System.out.println(e.getMessage());
                
            }

        } else {
            imageUrl = category.getImage();
        }

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(imageUrl);
        category.setStatus(categoryDto.getStatus());
        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        String img = categoryRepository.findById(id).get().getImage();
        String filename = img.substring(img.lastIndexOf("/") + 1);
        this.handleImage.deleteImage(filename);
        categoryRepository.deleteById(id);
    }


}
