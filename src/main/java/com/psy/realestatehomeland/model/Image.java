package com.psy.realestatehomeland.model;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

//    @ManyToOne
//    @JoinColumn(name = "property_id")
////    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private Property property;


    public byte[] getPhoto() throws IOException {
        return Files.readAllBytes(Paths.get("/tmp/",filename+extension));
    }
}
