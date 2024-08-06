package com.mycompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String title;
    private String description;
    private Double price;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY) //While getting parent data is not getting child data
    //@OneToOne(fetch = FetchType.EAGER)//While getting parent data is get child data
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
}