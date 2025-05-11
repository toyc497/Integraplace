package com.api.integraplace.repository;

import com.api.integraplace.entity.BPR1Entity;
import com.api.integraplace.entity.ITEMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BPR1Repository extends JpaRepository<BPR1Entity,Long> {

    @Query("SELECT w FROM BPR1Entity w WHERE code = :code")
    BPR1Entity findByCode(@Param("code") String code);

}
