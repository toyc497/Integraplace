package com.api.integraplace.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ORDS")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class ORDSEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="code",length = 20,unique=true)
    private String code;

    @Column(name = "status", length = 8)
    private String status;

    @Column(name = "create_date")
    private Date data_doc;

    @Column(name = "total_price")
    private Double totalprice;

    @ManyToOne
    @JoinColumn(name="bpr1_client")
    private BPR1Entity bpr1Client;

    @OneToMany(mappedBy = "orderFather",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ORITEntity> item_collection = new ArrayList<>();

}
