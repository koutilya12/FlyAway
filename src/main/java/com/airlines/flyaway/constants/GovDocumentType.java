package com.airlines.flyaway.constants;

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
	
	
	

}
