package com.jeremy.deus.item;

import java.awt.image.BufferedImage;

import com.jeremy.deus.assets.Assets;

public enum ItemType {

	DAGGER(0),
	SHORTSWORD(1),
	RAPIER(2),
	LONGSWORD(3);

	private final BufferedImage texture;

	private ItemType(int spriteIndex) {
		if (!Assets.isImageLoaded("items")) Assets.loadImage("items", "/items.png");
		texture = Assets.sprite("items", spriteIndex, 16, 16);
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
