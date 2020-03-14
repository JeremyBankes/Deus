package com.jeremy.deus.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

public class DeusRadioButtonUI extends BasicRadioButtonUI {

	private final static DeusRadioButtonUI INSTANCE = new DeusRadioButtonUI();

	private static ImageIcon icon, selectedIcon;

	/**
	 * Makes all Buttons use the DeusButtonUI
	 */
	public static void use() {
		UIManager.put("RadioButtonUI", DeusRadioButtonUI.class.getName());
	}

	public static ComponentUI createUI(JComponent c) {
		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.createGraphics();
		g.setColor(DeusDisplayConstants.COLOR_COMPONENT);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(DeusDisplayConstants.COLOR_BORDER);
		g.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
		g.dispose();
		icon = new ImageIcon(image);

		image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		g.setColor(DeusDisplayConstants.COLOR_COMPONENT);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(DeusDisplayConstants.COLOR_BORDER);
		g.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
		g.fillRect(4, 4, 8, 8);
		g.dispose();
		selectedIcon = new ImageIcon(image);

		return INSTANCE;
	}

	/**
	 * Set the default look for all buttons
	 */
	@Override
	protected void installDefaults(AbstractButton button) {
		button.setOpaque(false);
		button.setIcon(icon);
		button.setSelectedIcon(selectedIcon);
		button.setFont(DeusDisplayConstants.FONT_REGULAR);
		button.setForeground(DeusDisplayConstants.COLOR_TEXT);
		super.installDefaults(button);
	}

}
