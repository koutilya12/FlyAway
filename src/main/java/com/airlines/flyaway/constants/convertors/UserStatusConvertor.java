package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.UserStatus;

@Converter(autoApply = true)
public class UserStatusConvertor  implements AttributeConverter<UserStatus, String> {

	@Override
	public String convertToDatabaseColumn(UserStatus attribute) {
		System.out.println(attribute.getValue());
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public UserStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : UserStatus.getStatus(dbData);
	}

}
