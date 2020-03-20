package com.jeremy.deus.item;

public enum ArmourType {

	STUDDED_LEATHER(10);

	private int armour;

	private ArmourType(int armour) {
		this.armour = armour;
	}

	public int getArmour() {
		return armour;
	}

}
