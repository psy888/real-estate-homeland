package com.psy.realestatehomeland.model;

import com.psy.realestatehomeland.model.lib.City;
import com.psy.realestatehomeland.model.lib.PriceType;
import com.psy.realestatehomeland.model.lib.PropertyAction;
import com.psy.realestatehomeland.model.lib.PropertyType;
import com.psy.realestatehomeland.model.user.UserEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "property", schema = "sc")
public class Property {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2gen")
    @GenericGenerator(name = "uuid2gen", strategy = "uuid2")
    private String id;

    @Column(name = "type_id")
    @ManyToOne
    private PropertyType propertyType;

    @Column(name = "action_id")
    @ManyToOne
    private PropertyAction propertyAction;

    @Column(name = "area")
    private Double area;

    @Column(name = "bathroom_number")
    private Integer bathroomCnt;

    @Column(name = "bedroom_number")
    private Integer bedroomCnt;


    @Column(name = "price")
    private Double price;

    @Column(name = "price_type_id")
    @ManyToOne
    private PriceType priceType;


    @Column(name = "address")
    private String address;

    @Column(name = "city_id")
    @ManyToOne
    private City city;

    @Column(name = "agent_id")
    @ManyToOne
    private UserEntity agent;

}
