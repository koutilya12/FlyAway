package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.FlightStatus;

@Converter(autoApply = true)
public class FlightStatusConvertor implements AttributeConverter<FlightStatus, String> {

	@Override
	public String convertToDatabaseColumn(FlightStatus attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public FlightStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : FlightStatus.getStatus(dbData);
	}
}
