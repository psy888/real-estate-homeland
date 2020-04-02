package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.model.lib.PropertyAction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, String> {

    //by area from to
    List<Property> findByAreaGreaterThanEqualAndAreaLessThanEqual(int from, int to);

    List<Property> findAllByPropertyAction(PropertyAction action);

    @Query("SELECT p,c,pt FROM Property p JOIN FETCH  p.city c JOIN FETCH p.propertyType pt WHERE " +
            "(:type is null or pt.type = :type and (:subtype is null or pt.subType = :type)) and " +
            "(:action is null or p.propertyAction = :action) and " +
            "(:city is null or c.name = :city) and " +
            "(:address is null or p.address = :address) and " +
            "((:areaF is null and :areaTo is null) or (p.area > :areaF and p.area < :areaTo)) and " +
            "(:bath is null or p.bathroomCnt = :bath) and " +
            "(:bed is null or p.bedroomCnt = :bed) and " +
            "((:priceF is null or p.price > :priceF) and (:priceTo is null or p.price < :priceTo))")
    List<Property> findAllWithParams(@Param("type") String type,
                                     @Param("subtype") String subtype,
                                     @Param("action") String action,
                                     @Param("city") String city,
                                     @Param("address") String address,
                                     @Param("areaF") Integer areaFrom,
                                     @Param("areaTo") Integer areaTo,
                                     @Param("bath") Integer bath,
                                     @Param("bed") Integer bed,
                                     @Param("priceF") Integer priceF,
                                     @Param("priceTo") Integer priceTo
    );

    @Query("SELECT p,c FROM Property p JOIN FETCH p.city c WHERE " +
            "(:bath is null or p.bathroomCnt = :bath) and " +
            "(:bed is null or p.bedroomCnt = :bed)")
    List<Property> findAllWith2Params(@Param("bath") Integer bath,
                                     @Param("bed") Integer bed);

}
