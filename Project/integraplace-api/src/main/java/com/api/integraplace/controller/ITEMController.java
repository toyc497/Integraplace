package com.api.integraplace.controller;

import com.api.integraplace.entity.ITEMEntity;
import com.api.integraplace.form.ITEMForm;
import com.api.integraplace.service.ITEMService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Item")
public class ITEMController {

    @Autowired
    private ITEMService _ITEMService;

    @PostMapping("/create")
    public ResponseEntity<ITEMEntity> saveITEM(@RequestBody @Valid ITEMForm itemForm){
        ITEMEntity itemServiceResponse = _ITEMService.saveITEM(itemForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemServiceResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ITEMEntity>> findAllITEM(){
        List<ITEMEntity> itemServiceResponse = _ITEMService.findAllITEM();

        return ResponseEntity.status(HttpStatus.OK).body(itemServiceResponse);
    }


}
