package com.sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Notification {
	private Set<String> contents;
	private boolean eligibile;
	private List<String> log;

	public Notification() {
		contents = new HashSet<>();
		log = new ArrayList<>();
		eligibile = true;
	}

	public boolean isEligibile() {
		return eligibile;
	}

	public void addContent(String content) {
		contents.add(content);
	}

	public void removeContent(String content) {
		contents.remove(content);
	}

	public void log(String message) {
		log.add(message);
	}

	public void omit(String message) {
		eligibile = false;
		log(message);
	}

	public Set<String> getContents() {
		return contents;
	}

	public List<String> getLog() {
		return log;
	}
}
