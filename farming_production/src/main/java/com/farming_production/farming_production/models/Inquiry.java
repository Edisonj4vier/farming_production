package com.farming_production.farming_production.models;

import java.sql.Date;

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
@Table(name="TBL_INQUIRIES")
@Getter
@Setter

public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "TITLE", nullable = false, length = 100)    
    private String title;

    @Column(name = "OBSERVATION")    
    private String observation;
    @Column(name = "DATE")    
    private Date date;
    
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID", nullable=false)
    private Product product;

}
