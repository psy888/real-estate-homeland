package com.psy.realestatehomeland.model.lib;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "cur_sign")
    private Character currencySign;

    @Column(name = "cur_code")
    private String currencyCode;

    @Column(name = "rate")
    private Double rateToUsd;

}
