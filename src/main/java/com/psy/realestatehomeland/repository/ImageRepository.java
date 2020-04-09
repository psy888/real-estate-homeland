package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image,String> {

    Optional<Image> deleteImageByFilename(String filename);


}
