package com.api.integraplace.repository;

import com.api.integraplace.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT n FROM UserEntity n WHERE email = :email ORDER BY id DESC LIMIT 1")
    UserEntity findByEmail(@Param("email") String email);

}
