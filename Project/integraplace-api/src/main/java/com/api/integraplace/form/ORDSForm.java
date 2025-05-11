package com.api.integraplace.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ORDSForm {

    private String code;
    private String status;
    private Date data_doc;
    private Double totalprice;
    private String bpr1Client;
    private List<ORITForm> items_collection;

}
