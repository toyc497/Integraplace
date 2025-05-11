package com.api.integraplace.controller;

import com.api.integraplace.form.CepInfoForm;
import com.api.integraplace.service.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/Externalapi")
public class ExternalAPIController {

    @Autowired
    private ExternalAPIService _ExternalAPIService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<CepInfoForm> getCepInfo(@PathVariable("cep") String cep) throws URISyntaxException, IOException, InterruptedException {

        return ResponseEntity.status(HttpStatus.OK).body(this._ExternalAPIService.getCepInfo(cep));

    }

}
