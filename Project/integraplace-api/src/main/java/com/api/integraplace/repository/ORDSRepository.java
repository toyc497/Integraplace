package com.api.integraplace.repository;

import com.api.integraplace.entity.ORDSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ORDSRepository extends JpaRepository<ORDSEntity, Long> {

    @Query("SELECT w FROM ORDSEntity w WHERE code = :code")
    Optional<ORDSEntity> findByCode(@Param("code") String code);

}
