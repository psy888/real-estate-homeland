package com.psy.realestatehomeland.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(generator = "uuid4fileName")
    @GenericGenerator(name = "uuid4fileName", strategy = "uuid2")
    private String filename;


}
