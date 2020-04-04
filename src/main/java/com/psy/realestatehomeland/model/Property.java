package com.psy.realestatehomeland.model;

import com.psy.realestatehomeland.model.lib.*;
import com.psy.realestatehomeland.model.user.UserEntity;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Column(name = "action_type")
    private String propertyAction;

    @Column(name = "area")
    private Integer area;

    @Column(name = "bathroom_number")
    private Integer bathroomCnt;

    @Column(name = "bedroom_number")
    private Integer bedroomCnt;


    @Column(name = "price")
    private Double price;

    /*
    * PER_DAY,
    PER_WEEK,
    PER_MOTH,
    PER_YEAR
    * */
    @Column(name = "rental_price_type")
    private Integer priceType;

//    @NotNull
    @Column(name = "currency_code")
    private String currencyCode;


    @Column(name = "address")
    private String address;

//    @NotNull
    @Column(name = "city")
    private String city;

    @JoinColumn(name = "agent_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne
    private UserEntity agent;

    @Column(name = "featured")
    private Boolean isFeatured;

    @Column(name = "promoted")
    private Boolean isPromoted;

//    @OneToMany
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//    private List<Photo> photoList;

    @OneToOne

    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Photo mainPhoto;

}
