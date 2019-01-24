package com.bskyb.internettv;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bskyb.internettv.thirdparty.MovieDb;
import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import com.bskyb.internettv.parental_control_service.ParentalControlService;
import com.bskyb.internettv.parental_control_service.ParentalControlServiceImpl;


public class Tests {
	ParentalControlService pcs;
	MovieService ms;
	@Before
	public void setup() {
		pcs = new ParentalControlServiceImpl();
		ms = new MovieDb();
	}
	@Test
	public void testU() throws TitleNotFoundException, TechnicalFailureException {
		Assert.assertTrue("U",ms.getParentalControlLevel("101 Dalmatians").equals("U"));
	}
	@Test
	public void testPG() throws TitleNotFoundException, TechnicalFailureException {
		Assert.assertTrue("PG",ms.getParentalControlLevel("Thor: Ragnarok").equals("PG"));
	}
	@Test
	public void test12() throws TitleNotFoundException, TechnicalFailureException {
		Assert.assertTrue("12",ms.getParentalControlLevel("Bohemian Rhapsody").equals("12"));
	}
	@Test
	public void test15() throws TitleNotFoundException, TechnicalFailureException {
		Assert.assertTrue("15",ms.getParentalControlLevel("Vikings").equals("15"));
	}
	@Test
	public void test18() throws TitleNotFoundException, TechnicalFailureException {
		Assert.assertTrue("18",ms.getParentalControlLevel("Alien").equals("18"));
	}
	@Test
	public void testnull() throws TitleNotFoundException {
		try {
			ms.getParentalControlLevel(null);
		} catch (TechnicalFailureException e) {
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testempty() throws TitleNotFoundException {
		try {
			ms.getParentalControlLevel("");
		} catch (TechnicalFailureException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testunknown() throws TechnicalFailureException {
		try {
			ms.getParentalControlLevel("Fred");
		} catch (TitleNotFoundException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void test1212() throws Exception {
		Assert.assertTrue("12 = 12",pcs.canWatchMovie("12","Bohemian Rhapsody") ==true);
	}
	@Test
	public void test12PG() throws Exception {
		Assert.assertTrue("12 > PG",pcs.canWatchMovie("12","Thor: Ragnarok") == true);
	}
	@Test
	public void test1218() throws Exception {
		Assert.assertTrue("12 > PG",pcs.canWatchMovie("12","Alien") == false);
	}
	@Test
	public void test12null() {
		try {
			Assert.assertTrue("12 > PG",pcs.canWatchMovie("12",null) == false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	@Test
	public void test12empty() {
		try {
			Assert.assertTrue("12 > PG",pcs.canWatchMovie("12","") == false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	@Test
	public void test12unknown() {
		try {
			Assert.assertTrue("12 > PG",pcs.canWatchMovie("12","Fred") == false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	

}


