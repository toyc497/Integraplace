package com.api.integraplace.controller;

import com.api.integraplace.entity.ORDSEntity;
import com.api.integraplace.form.ORDSForm;
import com.api.integraplace.form.ORDSFormResponse;
import com.api.integraplace.service.ORDSService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class ORDSController {

    @Autowired
    private ORDSService _ORDSService;

    @GetMapping("/all")
    public ResponseEntity<List<ORDSEntity>> getAllORDS(){

        List<ORDSEntity> ordsListResponse = _ORDSService.findAllORDS();

        return ResponseEntity.status(HttpStatus.OK).body(ordsListResponse);
    }

    @GetMapping("/findbycode/{code}")
    public ResponseEntity<ORDSFormResponse> findByCode(@PathVariable("code") String codeAux){
        return ResponseEntity.status(HttpStatus.OK).body(_ORDSService.findORDSByCode(codeAux));
    }

    @PostMapping("/create")
    public ResponseEntity<ORDSFormResponse> saveORDS(@RequestBody @Valid ORDSForm _ORDSForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(_ORDSService.createORDS(_ORDSForm));
    }

    @PutMapping("/update/status/{code}/{status}")
    public void updateORDSStatus(@PathVariable("code") String code, @PathVariable("status") String status){
        this._ORDSService.updateStatus(code, status);
    }

}
