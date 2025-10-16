package com.api.integraplace.service;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.entity.WRHSEntity;
import com.api.integraplace.repository.WRHSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WRHSService {

    @Autowired
    private WRHSRepository _WRHSRepository;

    public WRHSEntity createWarehouse(WRHSEntity wrhs){

        long idLastBP = _WRHSRepository.count();

        String codeFormatted = "WRHS"+(idLastBP+1);
        wrhs.setCode(codeFormatted);
        wrhs.setActive("Y");

        return _WRHSRepository.save(wrhs);

    }

    public List<WRHSEntity> findAllWRHS(){
        return _WRHSRepository.findAll();
    }

    public void deleteWRHS(Long idAux) {
        _WRHSRepository.deleteById(idAux);
    }

    public WRHSEntity updateWarehouse(WRHSEntity wrhs) {

        Optional<WRHSEntity> wrhsBD = this._WRHSRepository.findById(wrhs.getId());
        WRHSEntity wrhsAux = null;

        if (wrhsBD.isPresent()){
            wrhsAux = wrhsBD.get();
        }

        assert wrhsAux != null;
        wrhsAux.setActive(wrhs.getActive());

        return this._WRHSRepository.save(wrhsAux);

    }
}
