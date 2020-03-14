package com.jeremy.deus.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import com.jeremy.deus.Deus;

public class DeusDisplayConstants {

	public static final Font FONT_REGULAR = new Font("SimplePixelFONT", Font.PLAIN, 16);
	public static final Font FONT_HEADER = new Font("Fool", Font.PLAIN, 32);

	public static final Color COLOR_BORDER = new Color(0x333333);
	public static final Color COLOR_COMPONENT = new Color(0xEEEEEE);
	public static final Color COLOR_BACKGROUND = new Color(0xFFFFFF);
	public static final Color COLOR_SELECTION = new Color(0xEEEEEE);

	public static void initiate() {
		try {
			GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, Deus.class.getResourceAsStream("/font/SimplePixelFONT.ttf")));
			environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, Deus.class.getResourceAsStream("/font/Fool.ttf")));
		} catch (Exception exception) {}
	}

}
