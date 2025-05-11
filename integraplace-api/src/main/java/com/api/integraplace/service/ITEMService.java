package com.api.integraplace.service;

import com.api.integraplace.entity.ITEMEntity;
import com.api.integraplace.entity.WRHSEntity;
import com.api.integraplace.form.ITEMForm;
import com.api.integraplace.repository.ITEMRepository;
import com.api.integraplace.repository.WRHSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITEMService {

    @Autowired
    private ITEMRepository _ITEMRepository;

    @Autowired
    private WRHSRepository _WRHSRepository;

    public ITEMEntity saveITEM(ITEMForm itemForm){
        Optional<WRHSEntity> wrhsAux = _WRHSRepository.findByCode(itemForm.getWrhs_father());
        WRHSEntity wrhsFather = null;

        if (wrhsAux.isPresent()){
            wrhsFather = wrhsAux.get();
        }else{
            return null;
        }

        ITEMEntity itemAux = new ITEMEntity();
        itemAux.setName(itemForm.getName());
        itemAux.setType(itemForm.getType());
        itemAux.setItem_length(itemForm.getItem_length());
        itemAux.setItem_height(itemForm.getItem_height());
        itemAux.setItem_width(itemForm.getItem_width());
        itemAux.setItem_weight(itemForm.getItem_weight());
        itemAux.setQuantity(itemForm.getQuantity());
        itemAux.setMinimal_quantity(itemForm.getMinimal_quantity());
        itemAux.setWrhs_father(wrhsFather);

        long idLastITEM = _ITEMRepository.count();

        String codeFormatted = "ITEM"+(idLastITEM+1);
        itemAux.setCode(codeFormatted);

        return _ITEMRepository.save(itemAux);

    }

    public List<ITEMEntity> findAllITEM(){
        return _ITEMRepository.findAll();
    }

}
