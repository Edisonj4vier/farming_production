package com.farming_production.farming_production.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_PRODUCTS")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="AMOUNT")
    private String amount;

    @ManyToOne
    @JoinColumn(name="MAINTENANCE_ID",nullable=false)
    private Maintenance maintenance;

    @OneToMany(mappedBy="product") //nombre del atributo en la clase B       
    private List<Inquiry> inquiry;
}
