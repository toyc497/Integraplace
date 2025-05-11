package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="BPR1")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class BPR1Entity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="code",length = 20,unique=true)
    private String code;

    @Column(name="fullname",length = 50,unique=true)
    @NotNull
    private String fullname;

    @Column(name = "cpf_cnpj", length = 11, unique = true)
    @NotNull
    private Long cpf_cnpj;

    @Column(name = "age", length = 3)
    private Integer age;

    @Column(name="type", length = 1)
    @NotNull
    private String type;

    @Column(name = "email", length = 50)
    @Email
    @NotNull
    private String email;

    @Column(name = "phone1", length = 11)
    @NotNull
    private Long phone1;

    @Column(name = "phone2", length = 11)
    private Long phone2;

    @Column(name = "cep", length = 8)
    @NotNull
    private Long cep;

    @Column(name="address", length = 50)
    @NotNull
    private String address;

    @Column(name = "streetnum", length = 5)
    private Integer streetnum;

    @Column(name="city", length = 50)
    @NotNull
    private String city;

    @Column(name="block", length = 50)
    @NotNull
    private String block;

    @Column(name="uf", length = 2)
    @NotNull
    private String uf;

    @OneToMany(mappedBy = "bpr1Client",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ORDSEntity> orders = new ArrayList<>();

}
