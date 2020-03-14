package com.jeremy.deus.tools;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class NameGenerator {

	private static String[] maleTitles, maleNames, femaleTitles, femaleNames;

	static {
		try {
			Properties names = new Properties();
			names.load(NameGenerator.class.getResourceAsStream("/names.properties"));

			maleTitles = names.getProperty("maleTitles", "").split(" *, *");
			maleNames = names.getProperty("maleNames", "").split(" *, *");
			femaleTitles = names.getProperty("femaleTitles", "").split(" *, *");
			femaleNames = names.getProperty("femaleNames", "").split(" *, *");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static String generateName(boolean male) {
		Random random = new Random();
		if (male) return maleTitles[random.nextInt(maleTitles.length)] + " " + maleNames[random.nextInt(maleNames.length)];
		else return femaleTitles[random.nextInt(femaleTitles.length)] + " " + femaleNames[random.nextInt(femaleNames.length)];
	}

	public static String generateName() {
		return generateName(Math.random() > 0.5);
	}

}
