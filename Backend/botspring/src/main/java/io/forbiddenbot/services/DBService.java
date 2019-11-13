package io.forbiddenbot.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.domain.enums.PartType;
import io.forbiddenbot.repositories.AdminRepository;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.repositories.ForbiddenRepository;

@Service
public class DBService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ExodiaPartRepository exodiaPartRepository;
	@Autowired
	private ForbiddenRepository forbiddenRepository;

	public void instantiateTesteDatabase() {

		Admin adm1 = new Admin(null, "fb.com/jvfaggion", "eumesmo", "euman123");
		Admin adm2 = new Admin(null, "fb.com/teste", "aaaa", "bbbb");

		ExodiaPart ex1 = new ExodiaPart(null, "image path", "Frodo", "192.168.1.1", new Date(), PartType.ARM, true,
				true, adm1);

		ExodiaPart ex2 = new ExodiaPart(null, "image path", "aaaaa", "192.168.1.2", new Date(), PartType.HEAD, true,
				true, adm1);

		ExodiaPart ex3 = new ExodiaPart(null, "image path", "bbbb", "192.168.1.3", new Date(), PartType.LEG, true, true,
				adm1);

		ExodiaPart ex4 = new ExodiaPart(null, "image path", "ccccc", "192.168.1.4", new Date(), PartType.HEAD, true,
				true, adm2);

		ExodiaPart ex5 = new ExodiaPart(null, "image path", "ddddd", "192.168.1.5", new Date(), PartType.ARM, true,
				false, adm2);

		Forbidden f1 = new Forbidden(null, new Date());
		Forbidden f2 = new Forbidden(null, new Date());

		f1.getExodiaParts().addAll(Arrays.asList(ex1, ex2, ex3, ex4, ex5));
		f2.getExodiaParts().addAll(Arrays.asList(ex5, ex4, ex3, ex4, ex5));

		ex1.getSourceFor().addAll(Arrays.asList(f1));
		ex2.getSourceFor().addAll(Arrays.asList(f1));
		ex3.getSourceFor().addAll(Arrays.asList(f1, f2));
		ex4.getSourceFor().addAll(Arrays.asList(f1, f2));
		ex5.getSourceFor().addAll(Arrays.asList(f1, f2));

		adm1.getVerifiedParts().addAll(Arrays.asList(ex1, ex2, ex3));
		adm2.getVerifiedParts().addAll(Arrays.asList(ex4, ex5));

		adminRepository.saveAll(Arrays.asList(adm1, adm2));
		exodiaPartRepository.saveAll(Arrays.asList(ex1, ex2, ex3, ex4, ex5));
		forbiddenRepository.saveAll(Arrays.asList(f1, f2));
	}

}