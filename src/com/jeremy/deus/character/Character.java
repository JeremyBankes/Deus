package com.jeremy.deus.character;

import java.awt.Image;

import com.jeremy.combat.Fighter;
import com.jeremy.deus.item.ArmourType;
import com.jeremy.deus.item.WeaponType;
import com.jeremy.sandbox.moves.ForfeitMove;

public abstract class Character extends Fighter {

	private CharacterClassType type;
	private WeaponType weaponType;
	private ArmourType armourType;

	private int energy;
	private int armour;

	public Character(String name, CharacterClassType type, int constitution, int dexterity, int fortitude) {
		super(name, 50 + constitution * 3);
		this.type = type;

		setLevel("constitution", constitution);
		setLevel("dexterity", dexterity);
		setLevel("fortitude", fortitude);

		getMoveSet().add(new ForfeitMove());
	}

	public void prepareForBattle() {
		setHealth(getMaxHealth());
		setEnergy(getMaxEnergy());
	}

	public abstract Image getTexture();

	public int getMaxEnergy() {
		int x = getLevel("dexterity");
		return (int) (10 * Math.sqrt(x));
	}

	public int getMaxArmour() {
		if (armourType == null) return 0;
		return armourType.getArmour();
	}

	public int getConstitution() {
		return getLevel("constitution");
	}

	public int getFortitude() {
		return getLevel("fortitude");
	}

	public int getDexterity() {
		return getLevel("dexterity");
	}

	public int getPower() {
		return (getConstitution() + getFortitude() + getDexterity()) / 3;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public ArmourType getArmourType() {
		return armourType;
	}

	public void setArmourType(ArmourType armourType) {
		this.armourType = armourType;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public CharacterClassType getType() {
		return type;
	}

}
