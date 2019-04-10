package com.sample;

import static org.junit.Assert.*;
import static com.sample.Trip.RouteType.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ContentsTest {

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
		session = container.newKieSession("ContentKS");
		session.getAgenda().getAgendaGroup("content").setFocus();
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
	public void defaultContents() {
		Notification notification = new Notification();
		session.insert(notification);
		session.fireAllRules();
		assertTrue("Cannot find expected content", notification.getContents().contains("HEADER"));
		assertTrue("Cannot find expected content", notification.getContents().contains("FOOTER"));
	}

	@Test
	public void internationalDinning() {
		Notification notification = new Notification();
		session.insert(notification);
		Trip trip = new Trip();
		trip.setRoute(INTERNATIONAL);
		session.insert(trip);

		session.fireAllRules();
		assertTrue("Cannot find expected content", notification.getContents().contains("INTERNATIONAL-DINNING"));
	}

}
