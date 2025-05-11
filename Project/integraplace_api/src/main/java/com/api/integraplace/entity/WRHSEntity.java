package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="WRHS")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class WRHSEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="code",length = 20,unique=true)
    private String code;

    @Column(name="name",length = 50,unique=true)
    @NotNull
    private String name;

    @Column(name = "cep", length = 8, unique = false)
    @NotNull
    private Long cep;

    @Column(name="address", length = 50, unique=false)
    @NotNull
    private String address;

    @Column(name = "streetnum", length = 5, unique = false)
    @NotNull
    private Integer streetnum;

    @Column(name="city", length = 50, unique=false)
    @NotNull
    private String city;

    @Column(name="block", length = 50, unique=false)
    @NotNull
    private String block;

    @Column(name="uf", length = 2, unique=false)
    @NotNull
    private String uf;

    @OneToMany(mappedBy = "wrhs_father",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ITEMEntity> items = new ArrayList<>();

}
