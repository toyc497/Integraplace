package com.api.integraplace.repository;

import com.api.integraplace.entity.ITEMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITEMRepository extends JpaRepository<ITEMEntity, Long> {

    @Query("SELECT w FROM ITEMEntity w WHERE code = :code")
    Optional<ITEMEntity> findByCode(@Param("code") String code);

}
