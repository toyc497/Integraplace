package com.api.integraplace.form;

import lombok.Data;

@Data
public class ORITForm {
    private String item_code;
    private Long quantity;
    private double unit_price;
    private double discount;
}
