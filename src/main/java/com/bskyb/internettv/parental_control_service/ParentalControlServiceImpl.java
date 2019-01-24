package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieDb;

public class ParentalControlServiceImpl implements ParentalControlService{

	String[] controls = {"U","PG","12","15","18"};
	@Override
	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception{
		MovieDb mdb = new MovieDb();
		String rating;
		try {
			rating = mdb.getParentalControlLevel(movieId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		int r = tokeniseRating(rating);
		int cl = tokeniseRating(customerParentalControlLevel);
		
		
		return r <= cl;

	}

	private int tokeniseRating(String ratingString) throws Exception {
		for(int i = 0; i < controls.length; i++) {
			if (ratingString.equals(controls[i])) {
				return i;
			}
		}
		throw new Exception("rating not found");
	}
}
