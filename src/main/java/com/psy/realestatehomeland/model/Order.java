package com.psy.realestatehomeland.model;

import com.psy.realestatehomeland.model.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order", schema = "sc")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @JoinColumn(name = "property_id")
    @ManyToOne
    private Property property;

    @Column(name = "rent_start")
    private LocalDateTime rentStartDate;

    @Column(name = "rent_end")
    private LocalDateTime rentEndDate;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private UserEntity customer;

}
