package com.psy.realestatehomeland.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photo", schema = "sc")
public class Image {

    @Id
    @GeneratedValue(generator = "uuid4fileName")
    @GenericGenerator(name = "uuid4fileName", strategy = "uuid2")
    private String filename;

    @Column(name = "ext")
    private String extension;

    @Column(name = "is_main")
    private Boolean isMain;
}
