package com.api.integraplace.repository;

import com.api.integraplace.entity.ORDSEntity;
import com.api.integraplace.entity.ORITEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ORITRepository extends JpaRepository<ORITEntity, Long> {

    @Query("SELECT w FROM ORITEntity w WHERE orderFather = :ords_id")
    Optional<List<ORITEntity>> findByORDS(@Param("ords_id") ORDSEntity ords_id);

}
