package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.UserTypes;

@Converter(autoApply = true)
public class UserTypesConvertor implements AttributeConverter<UserTypes, String>{

	@Override
	public String convertToDatabaseColumn(UserTypes attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public UserTypes convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : UserTypes.getStatus(dbData);
	}

}
