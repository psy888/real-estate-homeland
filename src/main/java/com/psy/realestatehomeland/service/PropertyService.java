package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository repository;


    /**
     * ["New York", 5]
     *
     * @return String[CityName, CountRepeatsInDb]
     */
    public List<String[]> getCitiesWithCnt() {
        return repository.findAllCities();
    }

    /**
     * ["commercial", 1]
     *
     * @return String[propertyType, CountRepeatsInDb]
     */
    public List<String[]> getPropertyTypes() {
        return repository.findAllPropertyTypes();
    }


    public List<Property> getPropertyByAgent(String email) {
        return repository.findAllByAgent_Email(email);
    }

    /**
     * Search property in db by params
     *
     * @param type   - propertyType  or "" (empty string by default)
     * @param action - propertyAction or "" (empty string by default)
     * @param city   - cityName or "" (empty string by default)
     * @return list of found property
     */
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

    public Property findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Property not found with id:" + id));
    }


    public Property addNewProperty(Property property) {
        return repository.save(property);
    }

    public void save(Property property) {
        repository.save(property);
    }

    /**
     * Update all fields except id and agent
     *
     * @param id - property to update in db
     * @param property - new property data
     */
    public void update(String id, Property property) {
        Property propertyToUpdate = this.findById(id);
        if (nonNull(propertyToUpdate)) {
            propertyToUpdate.setIsFeatured(property.getIsFeatured());
            propertyToUpdate.setIsPromoted(property.getIsPromoted());
            propertyToUpdate.setPrice(property.getPrice());
            propertyToUpdate.setCurrencyCode(property.getCurrencyCode());
            propertyToUpdate.setAddress(property.getAddress());
            propertyToUpdate.setCity(property.getCity());
            propertyToUpdate.setArea(property.getArea());
            propertyToUpdate.setBathroomCnt(property.getBathroomCnt());
            propertyToUpdate.setBedroomCnt(property.getBedroomCnt());
            propertyToUpdate.setDescription(property.getDescription());
            propertyToUpdate.setPropertyAction(property.getPropertyAction());
            repository.save(propertyToUpdate);
        }

    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
