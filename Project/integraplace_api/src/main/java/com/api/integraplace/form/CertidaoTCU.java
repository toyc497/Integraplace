package com.api.integraplace.form;

import lombok.Data;

@Data
public class CertidaoTCU {

    private String emissor;
    private String tipo;
    private String dataHoraEmissao;
    private String descricao;
    private String situacao;
    private String observacao;
    private String linkConsultaManual;
    private Integer tempoGeracao;

}
