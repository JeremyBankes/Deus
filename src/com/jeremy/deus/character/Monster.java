package com.jeremy.deus.character;

import java.awt.Image;
import java.util.Random;

import com.jeremy.combat.Battle;
import com.jeremy.deus.tools.NameGenerator;

public class Monster extends Character {

	public Monster(String name, CharacterClassType type, int constitution, int dexterity, int fortitude) {
		super(name, type, constitution, dexterity, fortitude);
	}

	@Override
	protected void takeTurn(Battle battle) {

	}

	@Override
	public Image getTexture() {
		return null;
	}

	public static Monster randomMonster(int power) {
		Random rng = new Random();
		CharacterClassType[] types = CharacterClassType.values();
		CharacterClassType type = types[rng.nextInt(types.length)];
		String name;
		if (type == CharacterClassType.WARRIOR) name = NameGenerator.getWarriorName();
		else if (type == CharacterClassType.RANGER) name = NameGenerator.getFantasyName();
		else name = NameGenerator.getMageName();

		int constitution = 0, dexterity = 0, fortitude = 0;
		for (int i = 0; i < power; i++) {
			int random = rng.nextInt(100);
			if (random < 33) constitution++;
			else if (random < 66) dexterity++;
			else fortitude++;
		}
		return new Monster(name, type, constitution, dexterity, fortitude);
	}

}
