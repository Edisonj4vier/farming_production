package com.farming_production.farming_production.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_PRODUCTS")
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long title;

    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="AMOUNT")
    private String amount;

    @ManyToOne
    @JoinColumn(name="MANTENIMIENTO_ID",nullable=false)
    private Mantenimiento mantenimiento;

}
