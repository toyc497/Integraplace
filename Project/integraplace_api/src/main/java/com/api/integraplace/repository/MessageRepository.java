package com.api.integraplace.repository;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.entity.MessageEntity;
import com.api.integraplace.entity.PORTALEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT s FROM MessageEntity s WHERE edital = :edital")
    List<MessageEntity> findByEditalId(@Param("edital") EditalEntity edital);

    @Query("SELECT m.message_date FROM MessageEntity m WHERE m.edital = :edital ORDER BY m.id DESC LIMIT 1")
    Date findLastDateByEdital(@Param("edital") EditalEntity edital);

}
