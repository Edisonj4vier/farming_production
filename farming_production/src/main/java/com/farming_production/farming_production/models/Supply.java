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
    private String expericionDate;

    @ManyToOne
    @JoinColumn(name="MAINTENANCE_ID", nullable=false)
    private Maintenance maintenance;    


}
