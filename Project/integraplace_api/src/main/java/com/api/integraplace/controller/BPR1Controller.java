package com.api.integraplace.controller;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.service.BPR1Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BussinessPartner1")
public class BPR1Controller {

    @Autowired
    private BPR1Service _BPR1Service;

    @GetMapping("/all")
    public ResponseEntity<List<BPR1Entity>> getAllBusinessPartner1(){

        return ResponseEntity.status(HttpStatus.OK).body(_BPR1Service.findAllBusinessPartners());

    }

    @PostMapping("/create")
    public ResponseEntity<BPR1Entity> saveBussinessPartner1(@RequestBody @Valid BPR1Entity bpr1){
        BPR1Entity bpr1Response = _BPR1Service.createBussinessPartner(bpr1);

        return ResponseEntity.status(HttpStatus.CREATED).body(bpr1Response);

    }

}
