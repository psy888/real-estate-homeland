package com.psy.realestatehomeland.model.lib;

/*
*   public enum Commercial{
        RETAIL,
        OFFICE,
        WAREHOUSE
    }

    public enum Living{
        HOUSE,
        APARTMENT,
        ROOM
    }
* */

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "property_type", schema = "sc")
public class PropertyType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "p_type")
    private String type;  //living / commercial

    @Column(name = "p_sub_type")
    private String subType; // warehouse / room / etc.


}
