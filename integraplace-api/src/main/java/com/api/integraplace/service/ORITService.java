package com.api.integraplace.service;

import com.api.integraplace.entity.ITEMEntity;
import com.api.integraplace.entity.NotificationEntity;
import com.api.integraplace.entity.ORDSEntity;
import com.api.integraplace.entity.ORITEntity;
import com.api.integraplace.form.ORITForm;
import com.api.integraplace.repository.ITEMRepository;
import com.api.integraplace.repository.NotificationRepository;
import com.api.integraplace.repository.ORDSRepository;
import com.api.integraplace.repository.ORITRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ORITService {

    @Autowired
    private ORITRepository _ORITRepository;

    @Autowired
    private ITEMRepository _ITEMRepository;

    @Autowired
    public ORDSRepository _ORDSRepository;

    @Autowired
    private NotificationRepository _NotificationRepository;

    public void findAllORDSID(String ordsCode){
        Optional<ORDSEntity> ordsAux = _ORDSRepository.findByCode(ordsCode);
        ORDSEntity ordsFounded = null;

        if(ordsAux.isPresent()){
            ordsFounded = ordsAux.get();
        }
        System.out.println(ordsFounded);

        Optional<List<ORITEntity>> listBD = _ORITRepository.findByORDS(ordsFounded);
        List<ORITEntity> listFounded = null;

        if(listBD.isPresent()){
            listFounded = listBD.get();
        }

    }

    public double saveORITList(ORDSEntity orderFatherID, List<ORITForm> listaORIT){
        double total = 0.0;

        for(ORITForm oritAux : listaORIT){

            if(oritAux.getUnit_price() != 0){
                double brutValue = oritAux.getQuantity() * oritAux.getUnit_price();
                total += brutValue - ((brutValue * oritAux.getDiscount()) / 100);
            }

            Optional<ITEMEntity> itemRequest = _ITEMRepository.findByCode(oritAux.getItem_code());
            ITEMEntity itemAux = null;

            if(itemRequest.isPresent()){
                itemAux = itemRequest.get();
            }

            long idLastORIT = _ORITRepository.count();
            String codeORIT = "ORIT"+(idLastORIT+1);

            ORITEntity oritToCreate = new ORITEntity();
            oritToCreate.setCode(codeORIT);
            oritToCreate.setUnit_price(oritAux.getUnit_price());
            oritToCreate.setDiscount(oritAux.getDiscount());
            oritToCreate.setQuantity(oritAux.getQuantity());
            oritToCreate.setOrderFather(orderFatherID);
            oritToCreate.setItemFather(itemAux);

            _ORITRepository.save(oritToCreate);

            assert itemAux != null;
            Long itemQuantityAux = itemAux.getQuantity() - oritAux.getQuantity();
            itemAux.setQuantity(itemQuantityAux);
            this._ITEMRepository.save(itemAux);

            if (itemQuantityAux <= itemAux.getMinimal_quantity()){
                String contentAux = "O Item "+ itemAux.getCode() +" Atingiu a quantidade mínima";

                NotificationEntity notificationAux = new NotificationEntity();
                notificationAux.setContent(contentAux);

                this._NotificationRepository.save(notificationAux);
            }
        }

        return total;
    }

}
