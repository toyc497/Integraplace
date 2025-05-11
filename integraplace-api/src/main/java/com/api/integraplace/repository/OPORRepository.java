package com.api.integraplace.repository;

import com.api.integraplace.entity.OPOREntity;
import com.api.integraplace.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OPORRepository extends JpaRepository<OPOREntity, String> {

    @Query("SELECT n FROM OPOREntity n WHERE participar is null ")
    List<OPOREntity> findNewOportunities();

    @Query("SELECT n FROM OPOREntity n WHERE participar = :participar")
    List<OPOREntity> findByParticipationOption(@Param("participar") String participarAux);

    @Query("SELECT COUNT(n) FROM OPOREntity n WHERE participar = :participate")
    Integer getQuantityByParticipate(@Param("participate") String participate);

}
