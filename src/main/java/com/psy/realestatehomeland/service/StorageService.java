package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Image;
import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.storage.StorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    //    private final Path rootLocation = Paths.get("src/main/resources/static/images/property/");
    private final Path rootLocation = Paths.get("/tmp/");
    private final ImageService imageService;
    private final PropertyService propertyService;

    /**
     * get file name from Photo.id field
     * Store photo to img/property
     *
     * @param file
     * @param property
     */
    public void store(MultipartFile file, Property property) {
        if (isNull(file)) {
            throw new StorageException("Error while uploading file.");
        }
        Image image = imageService.addNewPhoto();
        image.setExtension(getExtension(file));
//        property.getMainImage().add(image);
//        image.setProperty(property);

        try {
            if (!isRightExtension(file)) {
                throw new StorageException("Wrong file type  " + file.getName());
            }
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getName());
            }
            if (StringUtils.cleanPath(file.getOriginalFilename()).contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + file.getName());
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(image.getFilename() + getExtension(file)),
                        StandardCopyOption.REPLACE_EXISTING);

            }
            property.getMainImage().add(image);
            propertyService.save(property);

        } catch (IOException ioe) {
            imageService.deleteFromDB(image.getFilename());
            log.warn(ioe.getMessage());
            ioe.printStackTrace();
        }


    }

    /**
     * Store all non null photos to img/property
     * 0 index - is MAIN PHOTO
     *
     * @param list
     * @param property
     */
    public void storeAll(ArrayList<MultipartFile> list, Property property) {
        for (int i = 0; i < list.size(); i++) {
            if (nonNull(list.get(i)))
                store(list.get(i), property);
        }
    }

    /**
     * Check file extension
     *
     * @param file
     * @return
     */
    private boolean isRightExtension(MultipartFile file) {
        String ext = getExtension(file);
        if (isNull(ext)) {
            return false;
        }
        for (String s : ImageService.PHOTO_EXT) {
            if (ext.toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }

    private String getExtension(MultipartFile file) {
        if (nonNull(file) && file
                .getOriginalFilename()
                .lastIndexOf('.')!=-1) {
            return file.getOriginalFilename().substring(file
                    .getOriginalFilename()
                    .lastIndexOf('.'));
        }
        return null;
    }

    public void delete(Image img) throws IOException {
        if (isNull(img)) {
            throw new StorageException("file not found in db");
        }
        Files.delete(this.rootLocation.resolve(img.getFilename() + img.getExtension()));
    }
}
