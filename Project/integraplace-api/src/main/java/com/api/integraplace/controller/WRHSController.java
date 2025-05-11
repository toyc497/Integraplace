package com.api.integraplace.controller;

import com.api.integraplace.entity.WRHSEntity;
import com.api.integraplace.service.WRHSService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Warehouse")
public class WRHSController {

    @Autowired
    private WRHSService _WRHSService;

    @PostMapping("/create")
    public ResponseEntity<WRHSEntity> saveWarehouse(@RequestBody @Valid WRHSEntity wrhs){
        WRHSEntity wrhsServiceResponse = _WRHSService.createWarehouse(wrhs);

        return ResponseEntity.status(HttpStatus.CREATED).body(wrhsServiceResponse);

    }

    @GetMapping("/all")
    public ResponseEntity<List<WRHSEntity>> findAllWarehouse(){
        List<WRHSEntity> listResponse = _WRHSService.findAllWRHS();

        return ResponseEntity.status(HttpStatus.OK).body(listResponse);

    }

}
