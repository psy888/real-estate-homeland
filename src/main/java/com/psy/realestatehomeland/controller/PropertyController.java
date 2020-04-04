package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.user.UserEntity;
import com.psy.realestatehomeland.service.PropertyService;
import com.psy.realestatehomeland.service.SearchQueryParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/")
    public String startPage(Model model){
        //promoted on Hero carousel
        model.addAttribute("promoted",propertyService.getPromoted());
        //search form
        model.addAttribute("searchQueryParams", new SearchQueryParams());
        model.addAttribute("cities",propertyService.getCitiesWithCnt());
        model.addAttribute("propertyTypes", propertyService.getPropertyTypes());
        //featured on main page
        model.addAttribute("featured", propertyService.getFeatured());



        return "index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model){
        model.addAttribute("property", propertyService.getPropertyById(id));

        return "property-details";
    }




}
