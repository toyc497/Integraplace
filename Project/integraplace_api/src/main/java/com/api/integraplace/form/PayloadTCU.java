package com.api.integraplace.form;

import lombok.Data;

import java.util.List;

@Data
public class PayloadTCU {

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String uf;
    private List<CertidaoTCU> certidoes;
    private String certidaoPDF;
    private String seCnpjEncontradoNaBaseTcu;
    private Long dataHoraGeracaoInMillis;

}
