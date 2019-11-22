package io.forbiddenbot.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import io.forbiddenbot.RowThread;
import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.security.UserSS;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ExodiaPartService {
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ExodiaPartRepository repo;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
		
	public ExodiaPart find(Integer id) {
		Optional<ExodiaPart> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + ExodiaPart.class.getName()));
	}
	
	@Transactional
	public ExodiaPart insert(ExodiaPart part) {
//		RowThread.ipList.add(part.getUploaderIp());
		return repo.save(part);
	}
	
	public void verify(ArrayList<Integer> idList) {
		for(Integer id : idList) {
		ExodiaPart ex = find(id);
		repo.save(updateData(ex));
		}
	}
	
	public void delete(ArrayList<Integer> idList) {
		for(Integer id : idList) {
			find(id);
			try {
				repo.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("It is not possible to exclude verified exodia parts.");
			}
		}
	}
	
	public List<ExodiaPart> findAll() {
		return repo.findAll();
	}
	
	public Page<ExodiaPart> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverified(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverified(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverifiedArms(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedArms(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverifiedLegs(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedLegs(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverifiedHeads(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedHeads(pageRequest);
	}
	
	public Page<ExodiaPart> findVerified(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findVerified(pageRequest);
	}
	
	private ExodiaPart updateData(ExodiaPart newObj) {
		newObj.setIsVerified(true);
		UserSS user = UserService.authenticated();
		Admin adm = adminService.find(user.getId());
		adm.getVerifiedParts().add(newObj);
		newObj.setVerifier(adm);
		return newObj;
	}
	
	public String parseImg(String strImg, boolean leftOriented) {
		
		BufferedImage jpgImage = imageService.decodeToImage(strImg);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		if (!leftOriented) jpgImage = imageService.flip(jpgImage);
		String fileName = generateRandomString(8) + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"),fileName, "image").toString();
	}
	
	
	public URI uploadExodiaImage(MultipartFile multiPartFile) {
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = generateRandomString(8) + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
	
	public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }
}





