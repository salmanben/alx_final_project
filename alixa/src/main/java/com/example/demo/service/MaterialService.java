package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.MaterialDto;
import com.example.demo.model.Material;
import com.example.demo.repository.MaterialRepository;
import com.example.demo.util.HandleImage;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final HandleImage handleImage;


    public MaterialService(MaterialRepository materialRepository, HandleImage handleImage) {
        this.materialRepository = materialRepository;
        this.handleImage = handleImage;
    }

    public List<Material> getAllMaterials() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return materialRepository.findAll(sort);
    }

    public void saveMaterial(MaterialDto materialdto) {
        MultipartFile image = materialdto.getImage();
        String imageUrl = "";
        try{
            imageUrl  = this.handleImage.uploadImage(image);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            
        }

        Material material = new Material(materialdto.getName(), materialdto.getDescription(), imageUrl,
                materialdto.getPrice_by_day(), materialdto.getShipped_price(), materialdto.getStock(),
                materialdto.getCategory());
        materialRepository.save(material);

    }

    public MaterialDto getMaterial(int id) {
        Material material = materialRepository.findById(id).get();
        MaterialDto materialDto = new MaterialDto(material.getId(), material.getName(), material.getDescription(),
                material.getPrice_by_day(), material.getShipped_price(), material.getStock(), material.getCategory());
        return materialDto;
    }

    public Material getMaterialInstance(int id) {
        return materialRepository.findById(id).get();
    }

    public void updateMaterial(MaterialDto materialDto) {
        Material material = materialRepository.findById(materialDto.getId()).get();

        MultipartFile image = materialDto.getImage();

        String imageUrl = "";
        if (!image.isEmpty()) {
            try{
                String filename = material.getImage().substring(material.getImage().lastIndexOf("/") + 1);
                this.handleImage.deleteImage(filename);
                imageUrl  = this.handleImage.uploadImage(image);
            }
            catch(IOException e){
                System.out.println(e.getMessage());
                
            }

        } else {
            imageUrl = material.getImage();
        }
        material.setName(materialDto.getName());
        material.setDescription(materialDto.getDescription());
        material.setPrice_by_day(materialDto.getPrice_by_day());
        material.setShipped_price(materialDto.getShipped_price());
        material.setStock(materialDto.getStock());
        material.setCategory(materialDto.getCategory());
        material.setImage(imageUrl);
        materialRepository.save(material);

    }

    public void deleteMaterial(int id) {

        String img = materialRepository.findById(id).get().getImage();
        String filename = img.substring(img.lastIndexOf("/") + 1);
        this.handleImage.deleteImage(filename);
        materialRepository.deleteById(id);
    }

    // update material stock
    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }

    // find all materials by name containing or description containing
    public List<Material> search(String keyword) {
        return materialRepository.search(keyword);
    }

    public Page<Material> findAllWithPag(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    
}
