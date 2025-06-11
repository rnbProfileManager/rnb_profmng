package com.rnb.profmng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rnb.profmng.entity.profile.ProjectEmpInfo;
import com.rnb.profmng.entity.profile.ProjectEmpInfoPK;

@Repository
public interface ProjectEmpInfoRepo extends JpaRepository<ProjectEmpInfo, ProjectEmpInfoPK>{
	
}
