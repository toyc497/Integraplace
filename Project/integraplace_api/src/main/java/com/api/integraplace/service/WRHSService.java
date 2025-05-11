package com.api.integraplace.service;

import com.api.integraplace.entity.WRHSEntity;
import com.api.integraplace.repository.WRHSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WRHSService {

    @Autowired
    private WRHSRepository _WRHSRepository;

    public WRHSEntity createWarehouse(WRHSEntity wrhs){

        long idLastBP = _WRHSRepository.count();

        String codeFormatted = "WRHS"+(idLastBP+1);
        wrhs.setCode(codeFormatted);

        return _WRHSRepository.save(wrhs);

    }

    public List<WRHSEntity> findAllWRHS(){
        return _WRHSRepository.findAll();
    }

}
