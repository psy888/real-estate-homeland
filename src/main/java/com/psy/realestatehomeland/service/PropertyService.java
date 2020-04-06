package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository repository;

    public List<String[]> getCitiesWithCnt() {
        return repository.findAllCities();
    }

    public List<String[]> getPropertyTypes() {
        return repository.findAllPropertyTypes();
    }

    public Property getPropertyById(String id) {
        return repository.findById(id).orElse(new Property()); //???
    }

    public List<Property> getPropertyByAgent(String email) {
        return repository.findAllByAgent_Email(email);
    }

    public List<Property> searchProperty(String type, String action, String city) {
        return repository.findAllByPropertyTypeAndPropertyActionAndCity(
                ((type.isEmpty()) ? null : type),
                ((action.isEmpty()) ? null : action),
                ((city.isEmpty()) ? null : city));
    }


    public List<Property> getPromoted() {
        return repository.findTop5ByIsPromotedTrue();
    }

    public List<Property> getFeatured() {
        return repository.findTop9ByIsFeaturedTrue();
    }

    public Property findById(String id){
        return repository.findById(id).orElse(null); //todo replace to throw
    }


    public Property addNewProperty(Property property) {
        return repository.save(property);
    }

    public void update(Property property) {
        repository.save(property);
    }
}
