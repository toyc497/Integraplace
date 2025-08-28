package com.api.integraplace.form;

import com.api.integraplace.entity.EditalEntity;
import lombok.Data;

import java.util.Date;

@Data
public class EditalBotForm {

    private EditalEntity edital;
    private Date last_date;

}
