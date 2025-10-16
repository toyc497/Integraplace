package com.api.integraplace.service;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.repository.BPR1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        bpr1.setActive("Y");

        return _BPR1Repository.save(bpr1);

    }

    public BPR1Entity updateBussinessPartner(BPR1Entity bpr1) {
        Optional<BPR1Entity> bpr1BD = this._BPR1Repository.findById(bpr1.getId());
        BPR1Entity bpr1Aux = null;

        if (bpr1BD.isPresent()){
            bpr1Aux = bpr1BD.get();
        }

        assert bpr1Aux != null;
        bpr1Aux.setActive(bpr1.getActive());
        bpr1Aux.setEmail(bpr1.getEmail());
        bpr1Aux.setPhone1(bpr1.getPhone1());
        bpr1Aux.setPhone2(bpr1.getPhone2());
        bpr1Aux.setCep(bpr1.getCep());
        bpr1Aux.setAddress(bpr1.getAddress());
        bpr1Aux.setStreetnum(bpr1.getStreetnum());
        bpr1Aux.setCity(bpr1.getCity());
        bpr1Aux.setBlock(bpr1.getBlock());
        bpr1Aux.setUf(bpr1.getUf());

        return this._BPR1Repository.save(bpr1Aux);
    }
}
