package com.api.integraplace.repository;

import com.api.integraplace.entity.WRHSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WRHSRepository extends JpaRepository<WRHSEntity, Long> {

    @Query("SELECT w FROM WRHSEntity w WHERE code = :code")
    Optional<WRHSEntity> findByCode(@Param("code") String code);

}
