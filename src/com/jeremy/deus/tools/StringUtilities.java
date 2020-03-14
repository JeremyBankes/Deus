package com.jeremy.deus.tools;

public class StringUtilities {

	public static String beautify(String string) {
		String[] pieces = string.split("(?<!^|[-_. ])(?=[A-Z])|[-_. ]+");
		StringBuilder builder = new StringBuilder();
		String piece;
		for (int i = 0, len = pieces.length; i < len; i++) {
			piece = pieces[i].toLowerCase();
			if (i != 0) builder.append(' ');
			builder.append(piece);
			builder.setCharAt(builder.length() - piece.length(), Character.toTitleCase(piece.charAt(0)));
		}
		return builder.toString();
	}

}
