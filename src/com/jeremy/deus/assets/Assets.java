package com.jeremy.deus.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Assets {

	private static HashMap<String, BufferedImage> images = new HashMap<>();

	public static void loadImage(String name, String path) {
		try {
			images.put(name.toLowerCase(), ImageIO.read(Assets.class.getResourceAsStream(path)));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static boolean isImageLoaded(String name) {
		return images.containsKey(name);
	}

	public static BufferedImage getImage(String name) {
		if (!isImageLoaded(name)) throw new IllegalArgumentException("image " + name + " is not loaded");
		return images.get(name.toLowerCase());
	}

	public static BufferedImage sprite(BufferedImage source, int index, int spriteWidth, int spriteHeight) {
		int width = source.getWidth() / spriteWidth;
		return source.getSubimage(index % width * spriteWidth, index / width * spriteHeight, spriteWidth, spriteHeight);
	}

	public static BufferedImage sprite(String name, int index, int spriteWidth, int spriteHeight) {
		return sprite(getImage(name), index, spriteWidth, spriteHeight);
	}

}
