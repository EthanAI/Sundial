package calculationsandtests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SundialCalculationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHourLineAngles() {
		//No database of corrected sundial values exists. 
		//Nothing to test this module against
		//Will be tested via experimental use of printed sundial
		
		/*
		//abandoned test stub
		double longitude = 150;
		double latitude = 30;
		int date = 20130402;
		//double[] 
		//double[] testAngles = SundialCalculations.getHourLineAngles(latitude, longitude, date);
		
		//test southern hemisphere
		
		//test poles
		
		//test equator
		
		fail("Need experimental data to test against.");
		*/
	}
	
	@Test
	public void testGetLineLabels() {
		int[] standardLabels = {6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6};
		int[] DSTLabels = {7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7};
		assertArrayEquals(standardLabels, SundialCalculations.getLineLabels(30, 45, 20130101, 0));
		assertArrayEquals(DSTLabels, SundialCalculations.getLineLabels(30, 45, 20130601, 0));
		//confirm the hawaii override of DST
		//Seems we must test the connection of methods as well as the methods themselves. 
		//I see why SO reccomends testing the final master methods with a bazillion cases.
		assertArrayEquals(standardLabels, SundialCalculations.getLineLabels(21.5, -157.49, 20130101, 0));
		assertArrayEquals(standardLabels, SundialCalculations.getLineLabels(21.5, -157.49, 20130601, 0));
	}	
	
	@Test
	public void testGetGnomonAngle() {
		double testGnomon = Math.abs(-33.33);
		assertEquals(testGnomon, SundialCalculations.getGnomonAngle(-33.33), .01);
	}
	
	@Test
	public void testIsNorthernHemisphere() {
		assertTrue(SundialCalculations.isNorthernHemisphere(90));
		assertTrue(SundialCalculations.isNorthernHemisphere(45));
		assertTrue(SundialCalculations.isNorthernHemisphere(0));
		
		assertFalse(SundialCalculations.isNorthernHemisphere(-90));
		assertFalse(SundialCalculations.isNorthernHemisphere(-45));
		assertFalse(SundialCalculations.isNorthernHemisphere(-0.00001));
	}

	@Test
	public void testGetMeridianDelta() {
		double testDelta = SundialCalculations.getMeridianDelta(50);
		assertEquals(5, testDelta, 0);
	}

	@Test
	public void testEOTCorrection() {
		double testDelta = SundialCalculations.EOTCorrection(20130215);
		assertEquals(-15, testDelta, 0.5);
		testDelta = SundialCalculations.EOTCorrection(20131101);
		assertEquals(16, testDelta, 0.5);
	}

	@Test
	public void testGetDayNumber() {
		int testDay = SundialCalculations.getDayNumber(20130101);
		int year = 0;
		int month = 1;
		int day = 1;
		int[] dayArray = {1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335}; 
		int[] dayArrayLeap = {1, 32, 61, 92, 122, 153, 183, 214, 245, 275, 306, 336};
		
		assertEquals(1, testDay);
		for(year = 2013, month = 1; month <= 12; month++) {
			int date = year * 10000 + month * 100 + day;
			testDay = SundialCalculations.getDayNumber(date);
			assertEquals(dayArray[month - 1], testDay);
		}
		//test a known leapyear
		for(year = 2012, month = 1; month <= 12; month++) {
			int date = year * 10000 + month * 100 + day;
			testDay = SundialCalculations.getDayNumber(date);
			assertEquals(dayArrayLeap[month - 1], testDay);
		}
	}

	@Test
	public void testCosDegrees() {
		double cos = SundialCalculations.cosDegrees(0);
		assertEquals(1, cos, 0);
		cos = SundialCalculations.cosDegrees(45);
		assertEquals(0.707, cos, 0.001);
		cos = SundialCalculations.cosDegrees(90);
		assertEquals(0, cos, 0.001);
	}

	@Test
	public void testSinDegrees() {
		double sin = SundialCalculations.sinDegrees(0);
		assertEquals(0, sin, 0);
		sin = SundialCalculations.sinDegrees(45);
		assertEquals(0.707, sin, 0.001);
		sin = SundialCalculations.sinDegrees(90);
		assertEquals(1, sin, 0.001);
	}

	@Test
	public void testTanDegrees() {
		double tan = SundialCalculations.tanDegrees(0);
		assertEquals(0, tan, 0);
		tan = SundialCalculations.tanDegrees(45);
		assertEquals(1, tan, 0.001);
		tan = SundialCalculations.tanDegrees(90);
		assert tan > 100000000000000.0;
	}

	@Test
	public void testAtanDegrees() {
		double atan = SundialCalculations.atanDegrees(0);
		assertEquals(0, atan, 0);
		atan = SundialCalculations.atanDegrees(1);
		assertEquals(45, atan, 0.001);
		atan = SundialCalculations.atanDegrees(100000000000000.0);
		assertEquals(90, atan, 0.001);
	}

	@Test
	public void testIsDayLightSavings() {
		int dstYes = 2;
		int dstNo = 1;
		int dstEstimate = 0;
		//test begin border
		assertFalse(SundialCalculations.isDayLightSavings(0.0, 0.0, 20130309, dstEstimate));
		assertTrue(SundialCalculations.isDayLightSavings(0.0, 0.0, 20130309, dstYes));
		assertTrue(SundialCalculations.isDayLightSavings(0.0, 0.0, 20130310, dstEstimate));
		assertFalse(SundialCalculations.isDayLightSavings(0.0, 0.0, 20130310, dstNo));
		
		//test end border
		assertTrue(SundialCalculations.isDayLightSavings(0.0, 0.0, 20131102, dstEstimate));
		assertFalse(SundialCalculations.isDayLightSavings(0.0, 0.0, 20131102, dstNo));
		assertFalse(SundialCalculations.isDayLightSavings(0.0, 0.0, 20131103, dstEstimate));
		assertTrue(SundialCalculations.isDayLightSavings(0.0, 0.0, 20131103, dstYes));
		
		//test hawaii (non dst location)
		assertTrue(SundialCalculations.isDayLightSavings(21.351341,-157.49, 20130901, dstYes));
		assertFalse(SundialCalculations.isDayLightSavings(21.351341,-157.49, 20130901, dstEstimate));
		assertTrue(SundialCalculations.isDayLightSavings(21.351341,-157.49, 20130101, dstYes));
		assertFalse(SundialCalculations.isDayLightSavings(21.351341,-157.49, 20130101, dstEstimate));

	}

	@Test
	public void testIsUSASummer() {
		try {
			assertTrue(SundialCalculations.isUSASummer(20130413));
			assertFalse(SundialCalculations.isUSASummer(20130309));
			assertTrue(SundialCalculations.isUSASummer(20130310));
			assertTrue(SundialCalculations.isUSASummer(20131102));
			assertFalse(SundialCalculations.isUSASummer(20131103));
			SundialCalculations.isUSASummer(0);
			fail("Code should not execute as previous line should have triggered an exception.");
		}
		catch(Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testHasDSTLocation() {
		assertFalse(SundialCalculations.hasDSTLocation(21.306944400000000000, -157.858333300000030000));
		assertTrue(SundialCalculations.hasDSTLocation(0,0));
		assertTrue(SundialCalculations.hasDSTLocation(180, 180));
		assertTrue(SundialCalculations.hasDSTLocation(-180, -180));
	}

	@Test
	public void testWithinGlobeQuandrant() {
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(30, 0, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(31, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(-30, 0, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(-31, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, 15, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(0, 16, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, -15, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(0, -16, 30, -30, 15, -15));
		
		//test hawaii
		double hawaiiNLat = 30;
		double hawaiiSLat = 15;
		double hawaiiELong = -150;
		double hawaiiWLong = -170;
		double latitude = 21.351341;
		double longitude = -157.49;
		assertTrue(SundialCalculations.withinGlobeQuandrant(latitude, longitude, hawaiiNLat, hawaiiSLat, hawaiiELong, hawaiiWLong));
	}

}
