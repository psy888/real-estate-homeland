package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.service.PropertyService;
import com.psy.realestatehomeland.service.SearchQueryParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/")
    public String startPage(@RequestParam(defaultValue = "0", required = false) String viewType, Model model) {
        //promoted on Hero carousel
        model.addAttribute("promoted", propertyService.getPromoted());

        fillSearchForm(model);

        //featured on main page
        model.addAttribute("property", propertyService.getFeatured());
        //list or grid view
        model.addAttribute("view_type", viewType);
        setTitle(model, "Home");


        return "index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("property", propertyService.getPropertyById(id));

        setTitle(model, "Details");

        return "property-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String type,
                         @RequestParam(required = false) String action,
                         @RequestParam(required = false) String city,
                         @RequestParam(defaultValue = "0", required = false) String viewType,
                         Model model) {

        List<Property> found = propertyService.searchProperty(type,action,city);

        fillSearchForm(model);
        setTitle(model, "Search");

        model.addAttribute("property", found);

        //list or grid view
        model.addAttribute("view_type", viewType);

        return "search-result";
    }


    private void fillSearchForm(Model model) {
        //search form
        model.addAttribute("searchQueryParams", SearchQueryParams.builder().build());
        model.addAttribute("cities", propertyService.getCitiesWithCnt());
        model.addAttribute("propertyTypes", propertyService.getPropertyTypes());
    }

    private void setTitle(Model model, String title) {
        model.addAttribute("title", title);
    }
}
