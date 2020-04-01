package com.psy.realestatehomeland.model.lib;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city", schema = "sc")
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "city_name")
    private String name;

}
