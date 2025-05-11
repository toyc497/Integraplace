package com.api.integraplace.service;

import com.api.integraplace.entity.PORTALEntity;
import com.api.integraplace.form.PortalForm;
import com.api.integraplace.repository.PORTALRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalService {

    @Autowired
    private PORTALRepository _PORTALRepository;

    public PORTALEntity createPortal(PortalForm portalAux){

        PORTALEntity portal = new PORTALEntity();
        portal.setPortal_name(portalAux.getPortal_name());
        portal.setPortal_acronym(portalAux.getPortal_acronym());

        return _PORTALRepository.save(portal);

    }

}
