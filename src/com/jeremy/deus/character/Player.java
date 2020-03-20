package com.jeremy.deus.character;

import java.awt.Image;

import com.jeremy.combat.Battle;

public class Player extends Character {

	public Player(String name, CharacterClassType type, int constitution, int dexterity, int fortitude) {
		super(name, type, constitution, dexterity, fortitude);
	}

	@Override
	protected void takeTurn(Battle battle) {}

	@Override
	public Image getTexture() {
		return null;
	}

}
