package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Photo;
import com.psy.realestatehomeland.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoService {

    public static final String[] PHOTO_EXT = {"png","jpeg","jpg"};
    private final PhotoRepository repository;

    /**
     * Create new record in db and get id
     * @return id
     */
    public Photo addNewPhoto(){
        return repository.save(new Photo());
    }
}
