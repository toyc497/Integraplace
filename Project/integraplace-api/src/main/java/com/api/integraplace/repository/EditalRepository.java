package com.api.integraplace.repository;

import com.api.integraplace.entity.EditalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditalRepository extends JpaRepository<EditalEntity, Long> {
}
