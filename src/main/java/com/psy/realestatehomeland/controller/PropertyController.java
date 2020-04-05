package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.service.PropertyService;
import com.psy.realestatehomeland.service.SearchQueryParams;
import com.psy.realestatehomeland.service.StorageService;
import com.psy.realestatehomeland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final StorageService storageService;
    private final UserService userService;

    @GetMapping("/")
    public String startPage(@RequestParam(defaultValue = "0", required = false) String viewType, Model model, Principal principal) {
        //promoted on Hero carousel
        model.addAttribute("promoted", propertyService.getPromoted());

        fillSearchForm(model);

        //featured on main page
        model.addAttribute("property", propertyService.getFeatured());
        //list or grid view
        model.addAttribute("view_type", viewType);
        setTitle(model, "Home");

        setUserEmail(model, principal);


        return "index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model, Principal principal) {
        model.addAttribute("property", propertyService.getPropertyById(id));

        setTitle(model, "Details");
        setUserEmail(model, principal);

        return "property-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String type,
                         @RequestParam(required = false) String action,
                         @RequestParam(required = false) String city,
                         @RequestParam(defaultValue = "0", required = false) String viewType,
                         Model model,
                         Principal principal) {

        List<Property> found = propertyService.searchProperty(type, action, city);

        fillSearchForm(model);
        setTitle(model, "Search");

        model.addAttribute("property", found);

        //list or grid view
        model.addAttribute("view_type", viewType);

        setUserEmail(model, principal);

        return "search-result";
    }

    @GetMapping("/myAd/{email}")
    public String agentAds(@PathVariable String email, Model model, Principal principal) {
        if (!principal.getName().contentEquals(email)) {
            return "403";
        }
        model.addAttribute("property", propertyService.getPropertyByAgent(principal.getName()));

        setTitle(model, "Agent's Dashboard");

        setUserEmail(model, principal);


        return "agent-dashboard";
    }

    @GetMapping("/addAd")
    public String addAd(Model model, Principal principal) {

        model.addAttribute("newProperty", new Property());

        setTitle(model, "Add Property AD");
        setUserEmail(model, principal);
        return "add-ad";
    }


    @PostMapping("/addAd")
    public String handleFileUpload(@RequestParam("property") Property property, Model model, Principal principal) {
        property.setAgent(userService.findUserByEmail(principal.getName()));
        propertyService.addNewProperty(property);
        model.addAttribute(property);
        return "add-photo";
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

    private void setUserEmail(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("name", trimEmail(principal.getName()));
        }
    }

    private String trimEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }
}
