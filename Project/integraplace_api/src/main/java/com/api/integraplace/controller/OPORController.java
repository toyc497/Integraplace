package com.api.integraplace.controller;

import com.api.integraplace.entity.OPOREntity;
import com.api.integraplace.form.LicitacaoResultsForm;
import com.api.integraplace.service.OPORService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Opportunity")
public class OPORController {

    @Autowired
    private OPORService _OPORService;

    @GetMapping("/all")
    public ResponseEntity<List<OPOREntity>> getAllOpportunities(){

        return ResponseEntity.status(HttpStatus.OK).body(this._OPORService.findAllOpportunities());

    }

    @GetMapping("/my/participations/{option}")
    public ResponseEntity<List<OPOREntity>> findParticipationOpt(@PathVariable("option") String optionAux){

        return ResponseEntity.status(HttpStatus.OK).body(this._OPORService.findParticipationOportunities(optionAux));

    }

    @PutMapping("/update/participation")
    public ResponseEntity<OPOREntity> updateParticipationOpor(@RequestBody OPOREntity oporAux){

        return ResponseEntity.status(HttpStatus.OK).body(this._OPORService.updateParticipation(oporAux));

    }

    @GetMapping("/results")
    public ResponseEntity<LicitacaoResultsForm> getResultsLicitacao(){

        return ResponseEntity.status(HttpStatus.OK).body(this._OPORService.getResults());

    }

}
