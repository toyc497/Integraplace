package com.api.integraplace.form;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.entity.ORITEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ORDSFormResponse {

    private Long id;
    private String code;
    private String status;
    private Date data_doc;
    private Double totalprice;
    private BPR1Entity bpr1_client;
    private List<ORITEntity> orit_collection;

}
