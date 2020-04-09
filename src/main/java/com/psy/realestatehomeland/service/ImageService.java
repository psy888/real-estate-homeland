package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Image;
import com.psy.realestatehomeland.repository.ImageRepository;
import com.psy.realestatehomeland.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    public static final String[] PHOTO_EXT = {"jpeg", "jpg"};
    private final ImageRepository repository;
    private Image noImage; //create placeholder img;

    /**
     * Create new record in db and get id
     *
     * @return id
     */
    public Image addNewPhoto() {
        return repository.save(new Image());
    }

    public void deleteFromDB(String filename) {
        repository.deleteById(filename);
    }

    public Image findById(String fileName) {
        return repository.findById(fileName).orElse(noImage);
    }
}
