package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Photo;
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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    private final Path rootLocation = Paths.get("src/main/resources/static/images/property/");
    private final PhotoService photoService;
    private final PropertyService propertyService;

    /**
     * get file name from Photo.id field
     * Store photo to img/property
     *
     * @param file
     * @param property
     * @param isMain
     */
    public void store(MultipartFile file, Property property, boolean isMain) {

        Photo photo = photoService.addNewPhoto();
        photo.setExtension(getExtension(file));
        photo.setIsMain(isMain);
        photo.setProperty(property);
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
                Files.copy(inputStream, this.rootLocation.resolve(photo.getFilename() + getExtension(file)),
                        StandardCopyOption.REPLACE_EXISTING);

            }
            property.getMainPhoto().add(photo);


        } catch (IOException ioe) {
            log.warn(ioe.getMessage());
            photoService.delete(photo);
        }
        finally {
            propertyService.update(property);
        }


    }

    /**
     * Store all non null photos to img/property
     * 0 index - is MAIN PHOTO
     *
     * @param list
     * @param property
     */
    public void storeAll(MultipartFile[] list, Property property) {
        for (int i = 0; i < list.length; i++) {
            if (nonNull(list[i]))
                store(list[i], property, i == 0);
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
        for (String s : PhotoService.PHOTO_EXT) {
            if (ext.toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename().substring(file
                .getOriginalFilename()
                .lastIndexOf('.'));
    }
}
