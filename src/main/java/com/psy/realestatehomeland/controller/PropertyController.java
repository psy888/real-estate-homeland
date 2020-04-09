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
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;
    private final StorageService storageService;

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

        setUserName(model, principal);


        return "index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model, Principal principal) {
        model.addAttribute("property", propertyService.getPropertyById(id));

        setTitle(model, "Details");
        setUserName(model, principal);

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

        setUserName(model, principal);

        return "search-result";
    }

    @GetMapping("/myAd/{email}")
    public String agentAds(@PathVariable String email, Model model, Principal principal) {
        if (!principal.getName().contentEquals(email)) {
            //todo check role!!!!!!!!!!!!!!
            return "403";
        }

        model.addAttribute("property", propertyService.getPropertyByAgent(principal.getName()));

        setTitle(model, "Agent's Dashboard");

        setUserName(model, principal);


        return "agent-dashboard";
    }

    @GetMapping("/addAd")
    public String addAd(Model model, Principal principal) {

        model.addAttribute("newProperty", new Property());

        setTitle(model, "Add Property AD");
        setUserName(model, principal);
        return "add-ad";
    }

    @GetMapping("/addimageto")
    public String addImage(@RequestParam String id, Model model, Principal principal) {
        int imgCnt = propertyService.findById(id).getMainImage().size();
        if (imgCnt > 10) {
            return "redirect:/myAd/" + principal.getName();
        }
        model.addAttribute("propertyId", id);
        model.addAttribute("imageCnt", imgCnt);

        model.addAttribute("images", propertyService.findById(id).getMainImage());

        setTitle(model, "Add Property Photos");
        setUserName(model, principal);
        return "add-photo";
    }

    @PostMapping("/addAd")
    public String addProperty(Property property, Principal principal) {
        if(isNull(principal)){
            return "index";
        }
        property.setAgent(userService.findUserByEmail(principal.getName()));
        propertyService.addNewProperty(property);
        return "redirect:/addimageto?id=" + property.getId();
    }

    @PostMapping("/upload")
    public String uploadPhotos(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, Model model, Principal principal) {
        storageService.store(file, propertyService.findById(id));
        return "redirect:/addimageto?id=" + id;
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

    private void setUserName(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("name", trimEmail(principal.getName()));
        }
    }

    private String trimEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }

}
