package com.api.integraplace.service;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.entity.PORTALEntity;
import com.api.integraplace.form.EditalBotForm;
import com.api.integraplace.form.EditalForm;
import com.api.integraplace.repository.EditalRepository;
import com.api.integraplace.repository.MessageRepository;
import com.api.integraplace.repository.PORTALRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EditalService {

    @Autowired
    private PORTALRepository _PORTALRepository;

    @Autowired
    private EditalRepository _EditalRepository;

    @Autowired
    private MessageRepository _MessageRepository;

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

        assert portalAux != null;
        if (portalAux.getPortal_name().equals("Comprasnet")){
            String linkAux = "https://cnetmobile.estaleiro.serpro.gov.br/comprasnet-web/public/compras/acompanhamento-compra?compra=" + edital.getIdentifier();
            editalAux.setPortal_link(linkAux);
        }else{
            editalAux.setPortal_link("");
        }

        return _EditalRepository.save(editalAux);

    }

    public List<EditalEntity> getAll() {

        return _EditalRepository.findAll();

    }

    public void deleteEditalById(Long idAux) {

        _EditalRepository.deleteById(idAux);

    }

    public List<EditalBotForm> findAllBySystemName(String systemName) {
        List<EditalBotForm> editalBotFormList = new ArrayList<>();

        Optional<PORTALEntity> portalDB = _PORTALRepository.findByNamePortal(systemName);
        PORTALEntity portalAux = null;

        if(portalDB.isPresent()){
            portalAux = portalDB.get();
        }

        List<EditalEntity> editalList = _EditalRepository.findAllByPortal(portalAux);

        if (editalList.isEmpty()){
            return editalBotFormList;
        }

        for(EditalEntity edital : editalList){
            Date dateAux = _MessageRepository.findLastDateByEdital(edital);

            EditalBotForm editalBotForm = new EditalBotForm();
            editalBotForm.setEdital(edital);
            editalBotForm.setLast_date(dateAux);

            editalBotFormList.add(editalBotForm);

        }

        return editalBotFormList;
    }
}
