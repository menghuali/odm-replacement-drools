/**
 * 
 */
package com.sample;

import static org.junit.Assert.*;

import static com.sample.Trip.RouteType.*;
import static com.sample.Booking.BookingType.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author mli
 *
 */
public class CognitionRulesTest {
	private KieContainer container;
	private KieSession session;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// load up the knowledge base
		KieServices ks = KieServices.Factory.get();
		container = ks.getKieClasspathContainer();
		session = container.newKieSession("CognitionKS");
		session.getAgenda().getAgendaGroup("cognition").setFocus();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (session != null) {
			session.dispose();
			session.destroy();
		}
		if (container != null) {
			container.dispose();
		}
	}

	@Test
	public void tripRoute_Domestic() {
		Trip trip = new Trip();
		trip.setOriginCountry("Canada");
		trip.setDestCountry("Canada");

		session.insert(trip);
		session.fireAllRules();

		assertEquals("The trip route isn't expected", DOMESTIC, trip.getRoute());
	}

	@Test
	public void tripRoute_Transborder() {
		Trip trip = new Trip();
		trip.setOriginCountry("Canada");
		trip.setDestCountry("US");

		session.insert(trip);
		session.fireAllRules();

		assertEquals("The trip route isn't expected", TRANSBODER, trip.getRoute());
	}

	@Test
	public void tripRoute_International() {
		Trip trip = new Trip();
		trip.setOriginCountry("Canada");
		trip.setDestCountry("Mexico");

		session.insert(trip);
		session.fireAllRules();

		assertEquals("The trip route isn't expected", INTERNATIONAL, trip.getRoute());
	}

	@Test
	public void bookingType_GDS() {
		Booking booking = new Booking();
		booking.setPcc("TTY");

		session.insert(booking);
		session.fireAllRules();

		assertEquals("Booking type isn't expected", GDS, booking.getType());
	}

}
