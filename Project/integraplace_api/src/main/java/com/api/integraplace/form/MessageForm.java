package com.api.integraplace.form;

import lombok.Data;

import java.util.Date;

@Data
public class MessageForm {

    private String content;

    private String origin;

    private Date message_date;

    private Boolean read;

    private Long edital_id;

}
