package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
public class PropertyRepositoryTest {

    @Autowired
    PropertyRepository repository;

    @BeforeEach
    public void before() {
        Property prop1 = new Property();
        prop1.setArea(285);
        prop1.setBathroomCnt(2);
        prop1.setBedroomCnt(3);
        prop1.setCity("Zp");
        prop1.setPropertyType("Commercial");


        repository.save(prop1);

        Property prop2 = new Property();
        prop2.setArea(185);
        prop2.setBathroomCnt(1);
        prop2.setBedroomCnt(1);
        prop2.setCity("Dp");
        prop2.setPropertyType("Living");
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
                null);

        Assertions.assertEquals(285, list.get(0).getArea());

        List<Property> list2 = repository.findAllWithParams(null,
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
    public void cityQuery() {
        List<String[]> list = repository.findAllCities();
//        Map<String,Integer> list = repository.findAllCities();
        list.forEach(strings -> log.warn(strings.length + "  -  " + Arrays.toString(strings)));
        log.warn(list.toString());
//        Assertions.assertEquals(285, list.get(0).getArea());
//
//        List<Property> list2 = repository.findAllWith2Params(1,
//                null);
//
//        Assertions.assertEquals(185, list2.get(0).getArea());
    }
}
