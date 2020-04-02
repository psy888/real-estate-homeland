package com.psy.realestatehomeland.model;

import com.psy.realestatehomeland.model.lib.*;
import com.psy.realestatehomeland.model.user.UserEntity;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "property", schema = "sc")
public class Property {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2gen")
    @GenericGenerator(name = "uuid2gen", strategy = "uuid2")
    private String id;


//    @NotNull
    @JoinColumn(name = "type_id")
    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private PropertyType propertyType;

//    @NotNull
    @JoinColumn(name = "action_type")
    @ManyToOne
    private PropertyAction propertyAction;

    @Column(name = "area")
    private Integer area;

    @Column(name = "bathroom_number")
    private Integer bathroomCnt;

    @Column(name = "bedroom_number")
    private Integer bedroomCnt;


    @Column(name = "price")
    private Double price;

    @Column(name = "rental_price_type")
    private RentalPriceTerm priceType;

//    @NotNull
    @JoinColumn(name = "price_currency_id")
    @ManyToOne
    private Currency currency;


    @Column(name = "address")
    private String address;

//    @NotNull
    @JoinColumn(name = "city_id")
    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private City city;

    @JoinColumn(name = "agent_id")
    @ManyToOne
    private UserEntity agent;

}
