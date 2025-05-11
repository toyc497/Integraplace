package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PORTAL")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class PORTALEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String portal_name;

    private String portal_acronym;

    @OneToMany(mappedBy = "portal", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EditalEntity> edital_list = new ArrayList<>();

}
