package com.airlines.flyaway.constants;

import java.util.stream.Stream;

public enum GovDocumentType {
	
	AADHAAR("A"),
	PANCARD("P"),
	DRIVINGLICENSE("L"),
	VOTERID("V");

	private String value; 
	
	GovDocumentType(String string) {
		this.value = string;
	}
	
	public String getValue() { 
		return value;
	}
	
	public static void main(String[] args) {
		
		GovDocumentType govDocType = GovDocumentType.AADHAAR;
		System.out.println(govDocType.value);
	}
	
	public static GovDocumentType getGovType(String value) {
		return Stream.of(GovDocumentType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
	
}
