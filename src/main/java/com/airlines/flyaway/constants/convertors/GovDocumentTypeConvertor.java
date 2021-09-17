package com.airlines.flyaway.constants.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.airlines.flyaway.constants.GovDocumentType;

@Converter(autoApply = true)
public class GovDocumentTypeConvertor implements AttributeConverter<GovDocumentType, String> {

	@Override
	public String convertToDatabaseColumn(GovDocumentType attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public GovDocumentType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : GovDocumentType.getGovType(dbData);
	}
}

