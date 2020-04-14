package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Image;
import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.repository.ImageRepository;
import com.psy.realestatehomeland.storage.StorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    public static final String[] PHOTO_EXT = {"jpeg", "jpg"};
    private final ImageRepository repository;
    private final StorageService storageService;
    private final PropertyService propertyService;
    private Image noImage; //create placeholder img;


    /**
     * Create new record in db and get id
     * save file to local storage
     *
     * @return id
     */
    public void addNewPhoto(MultipartFile file, Property property) {
        Image image = repository.save(new Image());
        image.setExtension(getExtension(file));

        try {
            storageService.store(file, image);

            property.getMainImage().add(image);
            propertyService.save(property);

        } catch (StorageException se) {
            this.deleteFromDB(image.getFilename());
            log.warn(se.getMessage());
        }

    }

    public void deleteFromDB(String filename) {
        repository.deleteById(filename);
    }

    public Image findById(String fileName) {
        return repository.findById(fileName).orElse(noImage);
    }

    public byte[] getImageFile(String filename) throws IOException {
        Image image = this.findById(filename);
        return Files.readAllBytes(Paths.get(StorageService.IMG_FOLDER.toString(),image.getFilename() + image.getExtension()));
    }

    /**
     * get uploaded file extension
     *
     * @param file
     * @return .jpg , jpeg ...
     */
    private String getExtension(MultipartFile file) {
        if (nonNull(file) && file
                .getOriginalFilename()
                .lastIndexOf('.') != -1) {
            return file.getOriginalFilename().substring(file
                    .getOriginalFilename()
                    .lastIndexOf('.'));
        }
        return null;
    }
}
