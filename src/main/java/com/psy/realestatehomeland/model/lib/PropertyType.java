package com.psy.realestatehomeland.model.lib;

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

    @Column(name = "name")
    private String type;

}
