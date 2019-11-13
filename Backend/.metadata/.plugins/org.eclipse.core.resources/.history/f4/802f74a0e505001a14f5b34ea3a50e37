package io.forbiddenbot.services.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import io.forbiddenbot.RowThread;
import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.resources.exceptions.FieldMessage;


public class ExodiaPartValidator implements ConstraintValidator<ExodiaPartInsert, ExodiaPart> {

	
	@Override
	public void initialize(ExodiaPartInsert ann) {
	}

	@Override
	public boolean isValid(ExodiaPart obj, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		List<String> ipList = RowThread.ipList;
		int occurrences = Collections.frequency(ipList, obj.getUploaderIp());
		ipList.forEach(x -> System.out.println(x));
		
		
		
		if (occurrences > 3 ) list.add(new FieldMessage("uploaderIp", "Too many posts in a short period of time."));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}


}