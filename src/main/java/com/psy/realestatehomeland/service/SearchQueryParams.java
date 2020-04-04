package com.psy.realestatehomeland.service;

import lombok.Builder;
import lombok.Data;


@Data
public class SearchQueryParams {

    private String type;
    private String subtype;
    private String action;
    private String city;
    private String address;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer bath;
    private Integer bed;
    private Integer priceF;
    private Integer priceTo;

}
