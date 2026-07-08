package org.hexagon.javatest;

public class Car {
	private String type;
	private int hp;
	
	private Car() {}	
	
	public Car(String type, int hp) {
		super();
		this.type = type;
		this.hp = hp;
	}

	public String getType() {
		return type;
	}
	
	public int getHp() {
		return hp;
	}
}