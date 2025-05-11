package com.api.integraplace.controller;

import com.api.integraplace.entity.ORITEntity;
import com.api.integraplace.service.ORITService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/OrderItem")
public class ORITController {

    @Autowired
    private ORITService _ORITService;

    @GetMapping("/findByOrdsCode/{ordscode}")
    public void findOrderItemByORDSCode(@PathVariable("ordscode") String ordscode){
        _ORITService.findAllORDSID(ordscode);
        //return ResponseEntity.status(HttpStatus.OK).body(_ORITService.findAllORDSID(ordscode));

    }

}
