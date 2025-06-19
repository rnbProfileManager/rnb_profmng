package com.rnb.profmng.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnb.profmng.repository.profile.PjtHmnResrcInfoRepo;
import com.rnb.profmng.service.ProfileService;

@Controller
public class InsertProfile{

    private final PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo;

	@Autowired
	private ProfileService profileInsertService;

    InsertProfile(PjtHmnResrcInfoRepo pjtHmnResrcInfoRepo) {
        this.pjtHmnResrcInfoRepo = pjtHmnResrcInfoRepo;
    }

	@GetMapping("/empProfileInsert")
	public String showInsertProfilePage() {
		return "web/empProfileInsert";
	}
	
}