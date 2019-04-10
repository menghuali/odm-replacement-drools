package com.sample;

import static com.sample.Trip.RouteType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static com.sample.Guest.RewardTier.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class End2EndTest {
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
		session = container.newKieSession("ksession-process");
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
	public void internationalDinning() {
		Booking booking = new Booking();
		booking.setPcc("TTY");
		session.insert(booking);

		Trip trip = new Trip();
		trip.setOriginCountry("Canada");
		trip.setDestCountry("Mexico");
		session.insert(trip);

		Guest guest = new Guest();
		guest.setReward(GOLD);
		guest.setEmail("foo@test.com");
		session.insert(guest);

		Notification notification = new Notification();
		session.insert(notification);
		
		startProcess();

		assertTrue("Notification should be eligible", notification.isEligibile());
		assertEquals("The trip route isn't expected", INTERNATIONAL, trip.getRoute());
		assertTrue("Cannot find expected content", notification.getContents().contains("HEADER"));
		assertTrue("Cannot find expected content", notification.getContents().contains("FOOTER"));
		assertTrue("Cannot find expected content", notification.getContents().contains("INTERNATIONAL-DINNING"));
	}

	@Test
	public void gdsWithoutReward() {
		Booking booking = new Booking();
		booking.setPcc("TTY");
		session.insert(booking);

		Trip trip = new Trip();
		trip.setOriginCountry("Canada");
		trip.setDestCountry("Mexico");
		session.insert(trip);

		Guest guest = new Guest();
		guest.setEmail("foo@test.com");
		session.insert(guest);

		Notification notification = new Notification();
		session.insert(notification);

		startProcess();

		assertFalse("Notification should be ineligible", notification.isEligibile());
		assertTrue("Can't find expected omission", notification.getLog().contains("GDS booking without reward"));
	}
	
	private void startProcess() {
		session.startProcess("com.sample.bpmn.main");
	}

}
