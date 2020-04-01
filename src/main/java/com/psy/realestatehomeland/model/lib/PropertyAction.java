package com.psy.realestatehomeland.model.lib;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "price_action", schema = "sc")
public class PropertyAction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String action;
}
