package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.PassengerGender;
import com.airlines.flyaway.constants.UserStatus;

@Converter(autoApply = true)
public class PassengerGenderConvertor implements AttributeConverter<PassengerGender, String> {
	
	@Override
	public String convertToDatabaseColumn(PassengerGender attribute) {
		System.out.println(attribute.getValue());
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public PassengerGender convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : PassengerGender.getGender(dbData);
	}
	
}
