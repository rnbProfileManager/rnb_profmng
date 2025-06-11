package com.rnb.profmng.repository;

import com.rnb.profmng.entity.ProfileFileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileFileInfoRepository extends JpaRepository<ProfileFileInfoEntity, Long> {

}