package io.forbiddenbot.services.validation;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import io.forbiddenbot.dto.ExodiaPartNewDTO;
//import io.forbiddenbot.resources.exceptions.FieldMessage;
//import io.forbiddenbot.services.UserService;
import io.forbiddenbot.resources.exceptions.FieldMessage;


public class ExodiaPartValidator implements ConstraintValidator<ExodiaPartInsert, ExodiaPartNewDTO> {

	
	@Override
	public void initialize(ExodiaPartInsert ann) {
	}

	@Override
	public boolean isValid(ExodiaPartNewDTO obj, ConstraintValidatorContext context) {
		
//		List<FieldMessage> list = new ArrayList<>();
//		
//		if( UserService.authenticated() == null ) { 
//		List<String> ipList = RowThread.ipList;
//		int occurrences = Collections.frequency(ipList, obj.getUploaderIp());
//		
//		if (occurrences > 3 ) list.add(new FieldMessage("uploaderIp", "Too many posts in a short period of time."));
//		}
//		
//		for (FieldMessage e : list) {
//			context.disableDefaultConstraintViolation();
//			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
//					.addConstraintViolation();
//		}
//		return list.isEmpty();
		
		int paddingChars;
		if (obj.getImageStr().endsWith("==")) {
			paddingChars = 2;
		} else paddingChars = 1;
		 
		double size = (3 * (obj.getImageStr().length() / 4)) - (paddingChars);
		if (size > 5242880) {
			FieldMessage e = new FieldMessage("ImageStr", "File exceeded maximum size (5242880 BYTES).");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
			return false;
		} else return true;
	}


}