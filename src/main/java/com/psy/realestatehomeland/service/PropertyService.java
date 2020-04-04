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

    public List<Property> searchProperty(SearchQueryParams params){
        return repository.findAllWithParams(params.getType(),
                params.getSubtype(),
                params.getAction(),
                params.getCity(),
                params.getAddress(),
                params.getAreaFrom(),
                params.getAreaTo(),
                params.getBath(),
                params.getBed(),
                params.getPriceF(),
                params.getPriceTo());
    }


    public List<Property> getPromoted() {
        return repository.findTop5ByIsPromotedTrue();
    }

    public List<Property> getFeatured(){
        return repository.findAllByIsFeaturedTrue();
    }


}
