package com.farming_production.farming_production.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_SUPPLIES")
@Getter
@Setter
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AMOUNT")
    private short amount;
    @Column(name = "EXPERICION_DATE")
    private Date expericionDate;

    @OneToMany(mappedBy = "supply")
    private List<Maintenance> maintenances;


}
