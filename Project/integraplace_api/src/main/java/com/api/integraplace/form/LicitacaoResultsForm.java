package com.api.integraplace.form;

import lombok.Data;

@Data
public class LicitacaoResultsForm {

    Integer aceitas;
    Integer recusadas;

    public LicitacaoResultsForm(Integer aceitas, Integer recusadas) {
        this.aceitas = aceitas;
        this.recusadas = recusadas;
    }
}
