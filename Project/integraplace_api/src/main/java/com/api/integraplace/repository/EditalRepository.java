package com.api.integraplace.repository;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.entity.PORTALEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditalRepository extends JpaRepository<EditalEntity, Long> {

    @Query("SELECT e FROM EditalEntity e WHERE portal = :portal")
    List<EditalEntity> findAllByPortal(@Param("portal") PORTALEntity portal);

}
