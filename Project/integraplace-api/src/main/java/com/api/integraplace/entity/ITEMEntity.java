package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ITEM")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class ITEMEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="code",length = 20,unique=true)
    private String code;

    @Column(name="name",length = 50,unique=true)
    @NotNull
    private String name;

    @Column(name="type",length = 1,unique=false)
    @NotNull
    private String type;

    private Long quantity;

    private Long minimal_quantity;

    private double item_length;

    private double item_height;

    private double item_width;

    private double item_weight;

    @ManyToOne
    @JoinColumn(name="wrhs_father")
    private WRHSEntity wrhs_father;

    @OneToMany(mappedBy = "itemFather",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ORITEntity> oritList = new ArrayList<>();

}
