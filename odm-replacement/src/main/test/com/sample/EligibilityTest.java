package com.sample;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.Booking.BookingType;

public class EligibilityTest {

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
		session = container.newKieSession("EligibilityKS");
		session.getAgenda().getAgendaGroup("eligibility").setFocus();
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
	public void noEmail() {
		Guest guest = new Guest();
		Notification notification = new Notification();
		session.insert(guest);
		session.insert(notification);
		session.fireAllRules();

		assertFalse("Notification should be ineligible", notification.isEligibile());
		assertTrue("Can't find expected omission", notification.getLog().contains("Guest does not have email"));
	}
	
	@Test
	public void gdsWithoutReward() {
		Booking booking = new Booking();
		booking.setType(BookingType.GDS);
		Guest guest = new Guest();
		Notification notification = new Notification();
		session.insert(booking);
		session.insert(guest);
		session.insert(notification);
		session.fireAllRules();

		assertFalse("Notification should be ineligible", notification.isEligibile());
		assertTrue("Can't find expected omission", notification.getLog().contains("GDS booking without reward"));
	}

}
