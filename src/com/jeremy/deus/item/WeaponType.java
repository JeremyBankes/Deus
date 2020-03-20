package com.jeremy.deus.item;

import java.awt.image.BufferedImage;

import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.tools.StringUtilities;

public enum WeaponType {

	STURDY_DAGGER(0),
	ENGRAVED_SHORTSWORD(1),
	EXECUTIONER_GREATSWORD(2),
	ICE_WAND(3),
	BEJEWELED_STAFF(4),
	ANCIENT_BATTLE_STAFF(5),
	QUICK_SHOT_BOW(6),
	KEEN_EYE_SHORT_BOW(7),
	MAD_MANS_HUNTER(8);

	private final BufferedImage texture;
	private int heightening;
	private int grace;

	private WeaponType(int spriteIndex) {
		texture = Assets.sprite("weapons", spriteIndex, 128, 128);
		int tier = ordinal() % 3 + 1;
		heightening = tier * 5;
		grace = tier * 3;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public static WeaponType getByIndex(int index) {
		return values()[index];
	}

	public int getHeightening() {
		return heightening;
	}

	public int getGrace() {
		return grace;
	}

	@Override
	public String toString() {
		return StringUtilities.beautify(super.toString().toLowerCase());
	}

}
