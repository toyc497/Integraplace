package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="MESSAGE")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String origin;

    private Date message_date;

    private Boolean read;

    @ManyToOne
    @JoinColumn(name="edital_id")
    @JsonIgnore
    private EditalEntity edital;

}
