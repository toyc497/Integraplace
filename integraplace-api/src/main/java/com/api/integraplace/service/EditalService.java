package com.api.integraplace.service;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.entity.PORTALEntity;
import com.api.integraplace.form.EditalForm;
import com.api.integraplace.repository.EditalRepository;
import com.api.integraplace.repository.PORTALRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditalService {

    @Autowired
    private PORTALRepository _PORTALRepository;

    @Autowired
    private EditalRepository _EditalRepository;

    public EditalEntity createEdital(EditalForm edital) {

        Optional<PORTALEntity> portalDB = _PORTALRepository.findByNamePortal(edital.getPortal_name());
        PORTALEntity portalAux = null;

        if(portalDB.isPresent()){
            portalAux = portalDB.get();
        }

        EditalEntity editalAux = new EditalEntity();
        editalAux.setIdentifier(edital.getIdentifier());
        editalAux.setAgency(edital.getAgency());
        editalAux.setNotice(edital.getNotice());
        editalAux.setBatch(edital.getBatch());
        editalAux.setComment(edital.getComment());
        editalAux.setStatus("dispute");
        editalAux.setPortal(portalAux);

        return _EditalRepository.save(editalAux);

    }

    public List<EditalEntity> getAll() {

        return _EditalRepository.findAll();

    }

    public void deleteEditalById(Long idAux) {

        _EditalRepository.deleteById(idAux);

    }
}
