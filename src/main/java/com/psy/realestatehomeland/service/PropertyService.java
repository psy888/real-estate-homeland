package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository repository;

    public List<String[]> getCitiesWithCnt(){
        return repository.findAllCities();
    }

    public List<String[]> getPropertyTypes(){
        return repository.findAllPropertyTypes();
    }

    public Property getPropertyById(String id){
        return repository.findById(id).orElse(new Property()); //???
    }

    public List<Property> searchProperty(String type, String action, String city){
        return repository.findAllByPropertyTypeAndPropertyActionAndCity(
                ((type.isEmpty())?null:type),
                ((action.isEmpty())?null:action),
                ((city.isEmpty())?null:city));
    }


    public List<Property> getPromoted() {
        return repository.findTop5ByIsPromotedTrue();
    }

    public List<Property> getFeatured(){
        return repository.findAllByIsFeaturedTrue();
    }


}
