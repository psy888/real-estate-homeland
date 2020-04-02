package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.model.lib.City;
import com.psy.realestatehomeland.model.lib.PropertyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class PropertyRepositoryTest {

    @Autowired
    PropertyRepository repository;

    @BeforeEach
    public void before() {
        Property prop1 = new Property();
        prop1.setArea(285);
        prop1.setBathroomCnt(2);
        prop1.setBedroomCnt(3);
        City c = new City();
        c.setName("Zp");
        PropertyType pt = new PropertyType();
        pt.setType("Commercial");
        prop1.setCity(c);
        prop1.setPropertyType(pt);


        repository.save(prop1);

        Property prop2 = new Property();
        prop2.setArea(185);
        prop2.setBathroomCnt(1);
        prop2.setBedroomCnt(1);
        City c2 = new City();
        c2.setName("Dp");
        prop2.setCity(c2);
        PropertyType pt2 = new PropertyType();
        pt.setType("Living");
        prop2.setPropertyType(pt2);
        repository.save(prop2);

    }


    @Test
    public void allArgsQuery() {
        List<Property> list = repository.findAllWithParams(null,
                null,
                null,
                null,
                null,
                null,
                null,
                2,
                null,
                null,
                null);

        Assertions.assertEquals(285, list.get(0).getArea());

        List<Property> list2 = repository.findAllWithParams(null,
                null,
                null,
                null,
                null,
                null,
                null,
                1,
                null,
                null,
                null);

        Assertions.assertEquals(185, list2.get(0).getArea());
    }

    @Test
    public void twoArgsQuery() {
        List<Property> list = repository.findAllWith2Params(2,
                null);
        Assertions.assertEquals(285, list.get(0).getArea());

        List<Property> list2 = repository.findAllWith2Params(1,
                null);

        Assertions.assertEquals(185, list2.get(0).getArea());
    }
}
