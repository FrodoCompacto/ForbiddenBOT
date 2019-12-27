package io.forbiddenbot.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

import io.forbiddenbot.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Only PNG and JPG images are allowed.");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Failed to read file.");
		}
	}
	
	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Failed to read file.");
		}
	}
	
	public BufferedImage decodeToImage(String imageString) {
		boolean isPng = false;
		
		String ext = imageString.substring(11, imageString.indexOf(";"));
		System.out.println(ext);
		
		if (ext.equalsIgnoreCase("png")) isPng = true;
		else if (!ext.equalsIgnoreCase("jpeg")) {
			throw new FileException("Invalid file extension.");
		}
		
		imageString = imageString.substring(imageString.indexOf(",")+1);
		
        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.getDecoder().decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        if (isPng) image = pngToJpg(image);
		
        return image;
    }
	
	public BufferedImage flip(BufferedImage img) {
		int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flippedImage;
	}
	
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
			sourceImg, 
			(sourceImg.getWidth()/2) - (min/2), 
			(sourceImg.getHeight()/2) - (min/2), 
			min, 
			min);		
	}
	
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
	
	public BufferedImage fillTemplate(BufferedImage rArm, BufferedImage lArm, BufferedImage Head, BufferedImage rLeg, BufferedImage lLeg) {
		BufferedImage newImage = new BufferedImage(750,725, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = newImage.createGraphics();
		g2.setPaint(Color.CYAN);
        g2.fillRect(0, 0, 750, 725);
        
		try {
			BufferedImage template = ImageIO.read(new URL("https://forbidden-bot.s3-sa-east-1.amazonaws.com/template.jpg"));
	        g2.drawImage(template, null, 0, 0);
	        
	        g2.drawImage(rArm, null, 35,78);
	        g2.drawImage(Head, null, 286,78);
	        g2.drawImage(lArm, null, 536,79);
	        g2.drawImage(rLeg, null, 165,440);
	        g2.drawImage(lLeg, null, 417,440);
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g2.dispose();
		return newImage;
		
	}
	
	
}
