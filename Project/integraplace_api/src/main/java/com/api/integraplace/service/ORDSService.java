package com.api.integraplace.service;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.entity.NotificationEntity;
import com.api.integraplace.entity.ORDSEntity;
import com.api.integraplace.entity.ORITEntity;
import com.api.integraplace.form.*;
import com.api.integraplace.repository.BPR1Repository;
import com.api.integraplace.repository.NotificationRepository;
import com.api.integraplace.repository.ORDSRepository;
import com.api.integraplace.repository.ORITRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ORDSService {

    @Autowired
    private ORDSRepository _ORDSRepository;

    @Autowired
    private ORITService _ORITService;

    @Autowired
    private ORITRepository _ORITRepository;

    @Autowired
    private BPR1Repository _BPR1Repository;

    @Autowired
    private NotificationRepository _NotificationRepository;

    public List<ORDSEntity> findAllORDS(){

        return _ORDSRepository.findAll();

    }

    public ORDSFormResponse findORDSByCode(String codeAux) {
        Optional<ORDSEntity> ordsAuxDB = this._ORDSRepository.findByCode(codeAux);
        ORDSEntity ordsAux = null;

        if (ordsAuxDB.isPresent()){
            ordsAux = ordsAuxDB.get();
        }else{
            return null;
        }

        Optional<List<ORITEntity>> oritCollectionAux = this._ORITRepository.findByORDS(ordsAux);

        ORDSFormResponse ordsAuxResponse = new ORDSFormResponse();
        ordsAuxResponse.setId(ordsAux.getId());
        ordsAuxResponse.setCode(ordsAux.getCode());
        ordsAuxResponse.setStatus(ordsAux.getStatus());
        ordsAuxResponse.setData_doc(ordsAux.getData_doc());
        ordsAuxResponse.setTotalprice(ordsAux.getTotalprice());
        ordsAuxResponse.setBpr1_client(ordsAux.getBpr1Client());
        oritCollectionAux.ifPresent(ordsAuxResponse::setOrit_collection);

        return ordsAuxResponse;
    }

    public ORDSFormResponse createORDS(ORDSForm _ORDSForm){
        long idLastORDS = _ORDSRepository.count();
        String codeFormatted = "ORDS"+(idLastORDS+1);

        Optional<BPR1Entity> bpr1Aux = Optional.ofNullable(this._BPR1Repository.findByCode(_ORDSForm.getBpr1Client()));

        ORDSEntity ordsAux = new ORDSEntity();
        ordsAux.setCode(codeFormatted);
        ordsAux.setStatus("Open");
        ordsAux.setData_doc(new Date());
        ordsAux.setTotalprice(0.0);
        bpr1Aux.ifPresent(ordsAux::setBpr1Client);

        ORDSEntity orderResponse = _ORDSRepository.save(ordsAux);

        double totalValue = _ORITService.saveORITList(orderResponse, _ORDSForm.getItems_collection());

        orderResponse.setTotalprice(totalValue);

        ORDSEntity ordsResponseFinal = _ORDSRepository.save(orderResponse);

        String contentAux = "O pedido "+ ordsResponseFinal.getCode() +" foi criado";

        NotificationEntity notificationAux = new NotificationEntity();
        notificationAux.setContent(contentAux);

        this._NotificationRepository.save(notificationAux);

        Optional<List<ORITEntity>> oritCollectionAux = this._ORITRepository.findByORDS(ordsResponseFinal);

        ORDSFormResponse ordsAuxResponse = new ORDSFormResponse();
        ordsAuxResponse.setId(ordsResponseFinal.getId());
        ordsAuxResponse.setCode(ordsResponseFinal.getCode());
        ordsAuxResponse.setStatus(ordsResponseFinal.getStatus());
        ordsAuxResponse.setData_doc(ordsResponseFinal.getData_doc());
        ordsAuxResponse.setTotalprice(ordsResponseFinal.getTotalprice());
        ordsAuxResponse.setBpr1_client(ordsResponseFinal.getBpr1Client());
        oritCollectionAux.ifPresent(ordsAuxResponse::setOrit_collection);

        return ordsAuxResponse;
    }

    public void updateStatus(String code, String status) {
        if (status.equals("Open") || status.equals("Canceled") ||status.equals("Closed")){

            Optional<ORDSEntity> ordsAuxDB = this._ORDSRepository.findByCode(code);
            ORDSEntity ordsAux = null;

            if (ordsAuxDB.isPresent()){
                ordsAux = ordsAuxDB.get();
            }

            assert ordsAux != null;
            ordsAux.setStatus(status);

            this._ORDSRepository.save(ordsAux);

        }
    }
}
