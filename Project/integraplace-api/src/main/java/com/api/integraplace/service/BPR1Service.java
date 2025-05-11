package com.api.integraplace.service;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.repository.BPR1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BPR1Service {

    @Autowired
    private BPR1Repository _BPR1Repository;

    public List<BPR1Entity> findAllBusinessPartners(){

        return _BPR1Repository.findAll();

    }

    public BPR1Entity createBussinessPartner(BPR1Entity bpr1){

        long idLastBP = _BPR1Repository.count();

        String codeFormatted = "BPR1"+(idLastBP+1);
        bpr1.setCode(codeFormatted);

        return _BPR1Repository.save(bpr1);

    }

}
