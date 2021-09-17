package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.FlightBookingStatus;

@Converter(autoApply = true)
public class FlightBookingStatusConvertor implements AttributeConverter<FlightBookingStatus, String> {
	
	@Override
	public String convertToDatabaseColumn(FlightBookingStatus attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public FlightBookingStatus convertToEntityAttribute(String dbData) {
		System.out.println("hers"+dbData);
		return (dbData == null) ? null : FlightBookingStatus.getStatus(dbData);
	}

}
