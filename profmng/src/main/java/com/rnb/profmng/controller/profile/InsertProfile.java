package com.rnb.profmng.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnb.profmng.repository.profile.PjtHmnResrcInfoRepo;
import com.rnb.profmng.service.ProfileService;

@Controller
public class InsertProfile{

    private final PjtHmnResrcInfoRepo projectEmpInfoRepo;

	@Autowired
	private ProfileService profileInsertService;

    InsertProfile(PjtHmnResrcInfoRepo projectEmpInfoRepo) {
        this.projectEmpInfoRepo = projectEmpInfoRepo;
    }

	@GetMapping("/empProfileInsert")
	public String showInsertProfilePage() {
		return "web/empProfileInsert";
	}
	
}