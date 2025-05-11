package com.api.integraplace.repository;

import com.api.integraplace.entity.PORTALEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PORTALRepository extends JpaRepository<PORTALEntity, Long> {

    @Query("SELECT s FROM PORTALEntity s WHERE portal_name = :portal_name")
    Optional<PORTALEntity> findByNamePortal(@Param("portal_name") String portal_name);

}
