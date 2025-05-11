package com.api.integraplace.controller;

import com.api.integraplace.entity.PORTALEntity;
import com.api.integraplace.form.PortalForm;
import com.api.integraplace.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Portal")
public class PORTALController {

    @Autowired
    private PortalService _PortalService;

    @PostMapping("/save")
    public ResponseEntity<PORTALEntity> savePortal(@RequestBody PortalForm portalAux){
        return ResponseEntity.status(HttpStatus.CREATED).body(_PortalService.createPortal(portalAux));
    }

}
