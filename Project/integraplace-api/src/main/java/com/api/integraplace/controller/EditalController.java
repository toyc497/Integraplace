package com.api.integraplace.controller;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.form.EditalForm;
import com.api.integraplace.service.EditalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Edital")
public class EditalController {

    @Autowired
    private EditalService _EditalService;

    @PostMapping("/save")
    public ResponseEntity<EditalEntity> saveEdital(@RequestBody EditalForm edital){

        return ResponseEntity.status(HttpStatus.CREATED).body(_EditalService.createEdital(edital));

    }

    @GetMapping("/all")
    public ResponseEntity<List<EditalEntity>> findAllEdital(){

        return ResponseEntity.status(HttpStatus.OK).body(_EditalService.getAll());

    }

    @DeleteMapping("/delete/{Id}")
    public void deleteEdital(@PathVariable("Id") Long IdAux){

        _EditalService.deleteEditalById(IdAux);

    }

}
