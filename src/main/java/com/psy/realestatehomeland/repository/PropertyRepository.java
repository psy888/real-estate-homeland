package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, String> {

    //by area from to
    List<Property> findByAreaGreaterThanEqualAndAreaLessThanEqual(int from, int to);

    List<Property> findAllByPropertyAction(String action);

    List<Property> findTop5ByIsPromotedTrue();

    @Query("SELECT p,pt,a FROM Property p JOIN FETCH p.propertyType pt JOIN FETCH p.agent a WHERE " +
            "(:type is null or pt.type = :type and (:subtype is null or pt.subType = :type)) and " +
            "(:action is null or p.propertyAction = :action) and " +
            "(:city is null or p.city = :city) and " +
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

    @Query("SELECT p,pt FROM Property p JOIN FETCH p.propertyType pt WHERE (:type is null or pt.type = :type) " +
            "AND (:action is null or p.propertyAction = :action) " +
            "AND (:city is null or p.city = :city)")
    List<Property> findAllByPropertyTypeAndPropertyActionAndCity(String type, String action, String city);

    /**
     * all featured properties
     * @return list of properties
     */
    List<Property> findAllByIsFeaturedTrue();

    /**
     * Cities list
     * @return [unique value, count of repeats]
     */
    @Query("SELECT DISTINCT p.city,  COUNT(p.city) FROM Property p GROUP BY p.city")
    List<String[]> findAllCities();

    /**
     * Property types
     * @return [unique value, count of repeats]
     */
    @Query("SELECT DISTINCT pt.type,  COUNT(pt.type) FROM Property p JOIN p.propertyType pt GROUP BY pt.type")
    List<String[]> findAllPropertyTypes();


    List<Property> findAllByAgent_Email(String email);

    /**
     *  Featured List
     * @return Top 10 featured
     */
    List<Property> findTop9ByIsFeaturedTrue();

   //todo query min and max values by (price,area)






}
