package com.api.integraplace.controller;

import com.api.integraplace.DAO.CompetitorDAO;
import com.api.integraplace.service.CompetitorService;
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
@RequestMapping("/Copetitor")
public class CompetitorController {

    @Autowired
    private CompetitorService _CompetitorService;

    @GetMapping("/consult/{cnpj}")
    public ResponseEntity<CompetitorDAO> getCompetitorPendence(@PathVariable("cnpj") String cnpj_aux) throws URISyntaxException, IOException, InterruptedException {

        return ResponseEntity.status(HttpStatus.OK).body(_CompetitorService.analyseCompetitorPendence(cnpj_aux));

    }

}
