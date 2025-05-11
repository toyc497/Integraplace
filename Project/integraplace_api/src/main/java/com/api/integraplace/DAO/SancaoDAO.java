package com.api.integraplace.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SancaoDAO {

    public Long skSancao;
    public Integer skFatAcordosLeniencia;
    public Integer skFatCepim;
    public String cadastro;
    public String cpfCnpj;
    public String nomeSancionado;
    public String ufSancionado;

    public String categoriaSancao;
    public String orgao;
    public String dataPublicacao;
    public String valorMulta;
    public String dataInicialSancao;
    public String dataFinalSancao;
    public Integer quantidade;
    public String linkDetalhamento;

}
