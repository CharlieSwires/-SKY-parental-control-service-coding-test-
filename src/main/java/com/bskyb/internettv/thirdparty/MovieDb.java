package com.bskyb.internettv.thirdparty;

public class MovieDb implements MovieService {

	public String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException {
		if (titleId == null || titleId.isEmpty())
			throw new TechnicalFailureException("TechnicalFailureException");
		//U, PG, 12, 15, 18'
		String rating = "U";
		switch(titleId){
		case "Alien":
			rating = "18";
			break;
		case "101 Dalmatians":
			rating = "U";
			break;
		case "Thor: Ragnarok":
			rating = "PG";
			break;
		case "Bohemian Rhapsody":
			rating = "12";
			break;
		case "Vikings":
			rating = "15";
			break;
			
		default :
			throw new TitleNotFoundException("TitleNotFoundException");
			
		}
		return rating;
	}

}
