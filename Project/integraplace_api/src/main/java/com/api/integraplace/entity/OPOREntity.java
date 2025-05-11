package com.api.integraplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pncp_op")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class OPOREntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    private Date data_publicacao_pncp;
    private String url;
    private String orgao_cnpj;
    private String orgao_nome;
    private String description;
    private String esfera_nome;
    private String uf;
    private String modalidade_licitacao_nome;
    private String situacao_nome;

    @Column(length = 1)
    private String participar;

}
