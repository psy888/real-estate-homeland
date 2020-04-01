package com.psy.realestatehomeland.model.lib;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "price_type", schema = "sc")
public class PriceType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "price_name")
    private String type;

}
