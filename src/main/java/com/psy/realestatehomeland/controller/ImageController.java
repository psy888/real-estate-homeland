package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.service.ImageService;
import com.psy.realestatehomeland.service.PropertyService;
import com.psy.realestatehomeland.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;
    private final StorageService storageService;
    private final PropertyService propertyService;

    @GetMapping("/images/property/{fileName}")
    public void showImage(@PathVariable String fileName, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        try {
            response.getOutputStream().write(imageService.findById(fileName).getPhoto());
        } catch (IOException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/deleteImage")
    public String deleteImg(@RequestParam String imgid, @RequestParam String propid) {
        removeImgTotal(imgid, propid);
        return "redirect:/addimageto?id=" + propid;
    }


    /**
     * Upload image file and save to local storage
     *
     * @param id - property id
     * @param file - image file
     * @return to upload form
     */
    @PostMapping("/upload")
    public String uploadPhotos(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, Model model, Principal principal) {
        imageService.addNewPhoto(file, propertyService.findById(id));
        return "redirect:/addimageto?id=" + id;
    }


    //-------------Helper methods-------------------

    private void removeImgTotal(String imgid, String propid) {
        try {
            storageService.delete(imageService.findById(imgid));
            Property property = propertyService.findById(propid);
            property.getMainImage().removeIf(image -> image.getFilename().contentEquals(imgid));
            propertyService.save(property);
        } catch (IOException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
    }
}
