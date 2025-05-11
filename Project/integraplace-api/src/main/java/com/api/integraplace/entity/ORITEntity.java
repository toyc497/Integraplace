package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="ORIT")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class ORITEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="code",length = 20,unique=true)
    private String code;

    @NotNull
    private double unit_price;

    private double discount;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "ords_father")
    @JsonIgnore
    private ORDSEntity orderFather;

    @ManyToOne
    @JoinColumn(name = "item_father")
    private ITEMEntity itemFather;

}
