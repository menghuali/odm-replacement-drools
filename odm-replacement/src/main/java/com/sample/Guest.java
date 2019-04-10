package com.sample;

public class Guest {

	public static enum RewardTier {
		PLATINUM, GOLD, SILVER, TEAL, NULL
	}

	private String firstName;
	private String lastName;
	private String email;
	private RewardTier reward;
	private int age;
	
	public Guest() {
		reward = RewardTier.NULL;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RewardTier getReward() {
		return reward;
	}

	public void setReward(RewardTier reward) {
		this.reward = reward;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
