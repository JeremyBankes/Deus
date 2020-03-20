package com.jeremy.deus.tools;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class NameGenerator {

	private static final Random RNG = new Random();

	private static String[] maleTitles, maleNames, femaleTitles, femaleNames;
	private static String[] warriorNicknames, warriorNamesPiece1, warriorNamesPiece2;
	private static String[] femaleMageNames, maleMageNames, mageLastNames;
	private static String[] fantasySuffixes, fantasyPrefixes;

	static {
		try {
			Properties names = new Properties();
			names.load(NameGenerator.class.getResourceAsStream("/names.properties"));

			maleTitles = names.getProperty("maleTitles", "").split(" *, *");
			maleNames = names.getProperty("maleNames", "").split(" *, *");
			femaleTitles = names.getProperty("femaleTitles", "").split(" *, *");
			femaleNames = names.getProperty("femaleNames", "").split(" *, *");

			warriorNicknames = names.getProperty("warriorNicknames", "").split(" *, *");
			warriorNamesPiece1 = names.getProperty("warriorNamesPiece1", "").split(" *, *");
			warriorNamesPiece2 = names.getProperty("warriorNamesPiece2", "").split(" *, *");

			femaleMageNames = names.getProperty("femaleMageNames", "").split(" *, *");
			maleMageNames = names.getProperty("maleMageNames", "").split(" *, *");
			mageLastNames = names.getProperty("maleMageNames", "").split(" *, *");

			fantasyPrefixes = names.getProperty("fantasyPrefixes", "").split(" *, *");
			fantasySuffixes = names.getProperty("fantasySuffixes", "").split(" *, *");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static String choose(String[] list) {
		return list[RNG.nextInt(list.length)];
	}

	private static boolean choice() {
		return RNG.nextBoolean();
	}

	public static String getName(boolean male, boolean title) {
		String name = male ? choose(maleNames) : choose(femaleNames);
		if (title) name = (male ? choose(maleTitles) : choose(femaleTitles)) + " " + name;
		return name;
	}

	public static String getName() {
		return getName(choice(), choice());
	}

	public static String getWarriorName() {
		if (choice()) choose(warriorNicknames);
		return choose(warriorNamesPiece1) + choose(warriorNamesPiece2);
	}

	public static String getMageName(boolean male, boolean lastName) {
		String name = male ? choose(maleMageNames) : choose(femaleMageNames);
		if (lastName) name += " " + choose(mageLastNames);
		return name;
	}

	public static String getFantasyName(boolean male, boolean descriptor) {
		String name = getName(male, false);
		if (descriptor) {
			if (choice()) {
				name = choose(fantasyPrefixes) + " " + name;
			} else {
				name += " " + choose(fantasySuffixes);
			}
		}
		return name;
	}

	public static String getFantasyName() {
		return getFantasyName(choice(), true);
	}

	public static String getMageName() {
		return getMageName(choice(), true);
	}

}
