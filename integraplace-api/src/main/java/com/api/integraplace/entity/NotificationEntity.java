package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="NOTIFICATION")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="content",length = 100,unique=false)
    private String content;

}
