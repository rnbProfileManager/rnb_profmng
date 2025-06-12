package com.rnb.profmng.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.entity.profile.EmpNo;
import com.rnb.profmng.entity.profile.EmpNoPK;

@Repository
public interface EmpNoRepo extends JpaRepository<EmpNo, EmpNoPK>{
	
}
