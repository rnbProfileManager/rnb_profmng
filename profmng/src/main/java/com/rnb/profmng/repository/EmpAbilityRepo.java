package com.rnb.profmng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.entity.profile.EmpAbility;
import com.rnb.profmng.entity.profile.EmpAbilityPK;

@Repository
public interface EmpAbilityRepo extends JpaRepository<EmpAbility, EmpAbilityPK>{
	
}
