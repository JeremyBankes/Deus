package com.jeremy.deus.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import com.jeremy.deus.Deus;

public class DeusDisplayConstants {

	public static final Font FONT_HEADER = new Font("Righteous", Font.PLAIN, 32);
	public static final Font FONT_REGULAR = new Font("PT Mono", Font.PLAIN, 24);

	public static final Color COLOR_HEADER = new Color(0x5985b3);
	public static final Color COLOR_TEXT = new Color(0xD1E3DD);
	public static final Color COLOR_BORDER = new Color(0x045170);
	public static final Color COLOR_COMPONENT = new Color(0x001a29);
	public static final Color COLOR_BACKGROUND = new Color(0x2d3940);
	public static final Color COLOR_SELECTION = new Color(0x575366);

	public static void initiate() {
		try {
			GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, Deus.class.getResourceAsStream("/font/Righteous-Regular.ttf")));
			environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, Deus.class.getResourceAsStream("/font/PTMono-Regular.ttf")));
			DeusButtonUI.use();
			DeusRadioButtonUI.use();
			DeusLabelUI.use();
			DeusTextFieldUI.use();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
