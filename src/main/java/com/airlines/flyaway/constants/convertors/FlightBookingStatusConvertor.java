package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.FlightStatus;

@Converter(autoApply = true)
public class FlightBookingStatusConvertor implements AttributeConverter<FlightBookingStatus, String> {
	
	@Override
	public String convertToDatabaseColumn(FlightBookingStatus attribute) {
		System.out.println(attribute.getValue());
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public FlightBookingStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : FlightBookingStatus.getStatus(dbData);
	}

}
