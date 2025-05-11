package com.api.integraplace.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ITEMForm {

    private String name;

    @Column(name="type",length = 1,unique=false)
    @NotNull
    private String type;

    private double item_length;

    private double item_height;

    private double item_width;

    private double item_weight;

    private Long quantity;

    private Long minimal_quantity;

    private String wrhs_father;
}
