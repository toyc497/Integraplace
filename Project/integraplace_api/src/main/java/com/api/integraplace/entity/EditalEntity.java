package com.api.integraplace.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EDITAL")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class EditalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String agency;

    private String notice;

    private String batch;

    private String comment;

    private String status;

    private String portal_link;

    @ManyToOne
    @JoinColumn(name = "portal_id")
    private PORTALEntity portal;

    @OneToMany(mappedBy = "edital",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MessageEntity> messages = new ArrayList<>();

}
