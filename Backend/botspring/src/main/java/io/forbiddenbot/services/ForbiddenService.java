package io.forbiddenbot.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.domain.enums.PartType;
import io.forbiddenbot.repositories.ForbiddenRepository;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ForbiddenService {

	@Autowired
	private ForbiddenRepository repo;

	@Autowired
	private FacebookService facebookService;

	@Autowired
	private ExodiaPartService exodiaPartService;

	@Autowired
	private ImageService imageService;
	
	private ArrayList<String> uploadersList = new ArrayList<>();

	public Forbidden find(Integer id) {
		Optional<Forbidden> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Forbidden.class.getName()));
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"It is not possible to delete a summoned forbidden, he will delete you!!!");
		}
	}

	public List<Forbidden> findAll() {
		return repo.findAll();
	}

	public Page<Forbidden> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public boolean generateNewForbidden() {
		ArrayList<ExodiaPart> list = new ArrayList<>();
		ExodiaPart aux;
		BufferedImage postImg = null;

		List<ExodiaPart> listArms = exodiaPartService.getVerfifiedParts(PartType.ARM);
		list.add(listArms.get(getRandomNumberInRange(0, listArms.size())));
		do {
			aux = listArms.get(getRandomNumberInRange(0, listArms.size()));
		} while (list.get(0).equals(aux));
		list.add(aux);

		List<ExodiaPart> listHeads = exodiaPartService.getVerfifiedParts(PartType.HEAD);
		list.add(listHeads.get(getRandomNumberInRange(0, listHeads.size())));

		List<ExodiaPart> listLegs = exodiaPartService.getVerfifiedParts(PartType.LEG);
		list.add(listLegs.get(getRandomNumberInRange(0, listLegs.size())));
		do {
			aux = listLegs.get(getRandomNumberInRange(0, listLegs.size()));
		} while (list.get(3).equals(aux));
		list.add(aux);

		try {
			BufferedImage rArm = ImageIO.read(new URL((list.get(0).getImage())));
			BufferedImage lArm = ImageIO.read(new URL((list.get(1).getImage())));
			BufferedImage Head = ImageIO.read(new URL((list.get(2).getImage())));
			BufferedImage rLeg = ImageIO.read(new URL((list.get(3).getImage())));
			BufferedImage lLeg = ImageIO.read(new URL((list.get(4).getImage())));

			postImg = imageService.fillTemplate(imageService.flip(rArm), lArm, Head, imageService.flip(rLeg), lLeg);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (postImg == null) {
			return false;
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(postImg, "jpeg", os);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		list.forEach(x -> {
			if (x.getUploader() == null) {
				if (getRandomNumberInRange(0,1) == 0) {
					uploadersList.add("[DATA EXPUNGED]");
				} else uploadersList.add("[REDACTED]");
			}else if (x.getUploader().isEmpty()) {
				if (getRandomNumberInRange(0,1) == 0) {
					uploadersList.add("[DATA EXPUNGED]");
				} else uploadersList.add("[REDACTED]");
			} else uploadersList.add(x.getUploader());
		});
		
		String message = "Exodia! It’s not possible! No one has ever been able to call him!\n";
		String comment = "Uploaders:\n"
				+ "Left Arm: " + uploadersList.get(0) + "\n"
				+ "Right Arm: " + uploadersList.get(1) + "\n"
				+ "Head: " + uploadersList.get(2) + "\n"
				+ "Left Leg: " + uploadersList.get(3) + "\n"
				+ "Right Leg: " + uploadersList.get(4);
		
		String postID = facebookService.facebookPost(message, is);
		facebookService.commentPost(postID, comment);
		Forbidden frbdn = new Forbidden(null, new Date(), list, "https://www.facebook.com/forbiddenbot/photos/a.127168682087845/" + postID + "/");
		repo.save(frbdn);
		return true;

	}

	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - 1 - min) + 1) + min;
	}

}
